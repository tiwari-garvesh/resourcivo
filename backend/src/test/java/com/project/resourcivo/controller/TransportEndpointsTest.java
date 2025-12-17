package com.project.resourcivo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.resourcivo.dto.TransportCreateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransportEndpointsTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        @WithMockUser(username = "admin", roles = { "ADMIN" })
        public void testCreateTransportWithTimings() throws Exception {
                TransportCreateDTO dto = new TransportCreateDTO();
                dto.setVehicleName("Test Bus");
                dto.setRegistrationNumber("TEST-" + System.currentTimeMillis());
                dto.setVehicleType("Bus");
                dto.setCompany("TestCo");
                dto.setColor("Yellow");
                dto.setParkingNumber("P-01");
                dto.setRoutes("Route A -> Route B");
                dto.setTotalSeats(40);
                dto.setPurchaseDate(LocalDate.now());
                dto.setDriverName("John Doe");
                dto.setDescription("Test Description");
                dto.setIsActive(true);

                // Timings
                dto.setDepartureTime(LocalTime.of(8, 0));
                dto.setArrivalTime(LocalTime.of(9, 30));
                dto.setReturnTime(LocalTime.of(17, 0));

                // 1. Create Transport
                MvcResult result = mockMvc.perform(post("/api/transport")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.departureTime").value("08:00:00"))
                                .andExpect(jsonPath("$.arrivalTime").value("09:30:00"))
                                .andExpect(jsonPath("$.returnTime").value("17:00:00"))
                                .andExpect(jsonPath("$.vehicleName").value("Test Bus"))
                                .andReturn();

                // Extract ID for further tests
                String responseContent = result.getResponse().getContentAsString();
                com.fasterxml.jackson.databind.JsonNode jsonNode = objectMapper.readTree(responseContent);
                Long transportId = jsonNode.get("id").asLong();

                // 2. Test Student Booking
                // Endpoint: POST /api/transport/booking/student/{studentId}
                // Body: { "transportId": <id>, "pickupPoint": "..." }
                // Note: Using transportId as studentId implies we are booking for a student
                // with ID=transportId.
                // In a real integration test, we should probably ensure that student exists or
                // the service handles non-existent students gracefully (or mock it).
                // Since we are using an existing database (from logs), user "student1" might
                // not have ID = transportId.
                // However, the service method signature is:
                // bookTransport(Long studentId, RoleName role, TransportBookingRequestDTO dto)
                // It likely checks if user exists.
                // Let's use a dummy ID '999' which might fail if foreign key constraints exist.
                // BUT, we are mocking the USER in security context, but not the
                // SERVICE/REPOSITORY.
                // If the Service checks `userRepository.findById(studentId)`, then 999 will
                // fail.
                // We might need to ensure a student exists.
                // DataInitializer creates 'username' (admin?). It creates roles.
                // It does NOT explicitly create a student user unless I added it.
                // If the service checks for User existence, we need a valid User ID.
                // "student1" from @WithMockUser is a security principal, meaningless to JPA
                // unless synced.

                // Strategy: Use the "admin" user created by DataInitializer? But admin might
                // not be a STUDENT.
                // The service checks `if (!userRepository.existsById(userId))...`?
                // Let's try booking as the existing Admin user ID (usually 1 if H2, or whatever
                // if MySQL).
                // BUT `DataInitializer` creates `username` user.
                // We can't easily guess the ID.

                // Wait, `TransportController` delegates to `bookingService`.
                // `bookingService.bookTransport`:
                // checks `bookingRepository.findByUserIdAndRoleAndStatus(...)`.
                // Does it check user existence? Probably.
                // Tests on real DB are tricky without setup.

                // We will TRY 1L as student ID. If 500/404, we know why.

                // Ensure clean state for Student 1 by deleting any existing booking
                mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                                .delete("/api/transport/booking/student/1")
                                .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
                                                .user("student1").roles("STUDENT")));

                mockMvc.perform(post("/api/transport/booking/student/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"transportId\": " + transportId + ", \"pickupPoint\": \"Main Gate\"}")
                                .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
                                                .user("student1").roles("STUDENT")))
                                .andExpect(status().isCreated());

                // 3. Test One Booking Constraint (Student tries again)
                mockMvc.perform(post("/api/transport/booking/student/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"transportId\": " + transportId + ", \"pickupPoint\": \"Main Gate\"}")
                                .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
                                                .user("student1").roles("STUDENT")))
                                .andExpect(status().isConflict());

                // 4. Test Manager Access
                mockMvc.perform(post("/api/transport-manager")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createManagerDto()))
                                .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
                                                .user("admin").roles("ADMIN")))
                                .andExpect(status().isCreated());
        }

        private com.project.resourcivo.dto.TransportManagerCreateDTO createManagerDto() {
                com.project.resourcivo.dto.TransportManagerCreateDTO dto = new com.project.resourcivo.dto.TransportManagerCreateDTO();
                dto.setFirstName("Manager");
                dto.setLastName("One");
                dto.setEmail("manager" + System.currentTimeMillis() + "@test.com");
                dto.setPhoneNumber("1234567890");
                dto.setJoiningDate(LocalDate.now());
                return dto;
        }
}
