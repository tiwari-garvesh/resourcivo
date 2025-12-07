package com.project.resourcivo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.resourcivo.dto.EmergencyContactCreateDTO;
import com.project.resourcivo.dto.StudentCreateDTO;
import com.project.resourcivo.dto.StudentResponseDTO;
import com.project.resourcivo.service.IStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmergencyContactVerificationTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private IStudentService studentService;

        @Test
        @WithMockUser(username = "admin", roles = { "ADMIN" })
        public void verifyEmergencyContactLimit() throws Exception {
                // 1. Create a Student
                StudentCreateDTO studentDto = new StudentCreateDTO();
                studentDto.setFirstName("Test");
                studentDto.setLastName("Student");
                studentDto.setDateOfBirth(LocalDate.of(2000, 1, 1));
                // Removed course setting to avoid type mismatch or will set null if allowed
                // studentDto.setCourse("CS");
                // ... fill minimal required fields ...

                StudentResponseDTO createdStudent = studentService.createFromDto(studentDto);
                Long studentId = createdStudent.getId();

                // 2. Add 1st Emergency Contact
                EmergencyContactCreateDTO contact1 = new EmergencyContactCreateDTO();
                contact1.setName("Mom");
                contact1.setPrimaryContactNumber(1234567890L);

                mockMvc.perform(post("/api/student/" + studentId + "/emergency-contact")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(contact1)))
                                .andExpect(status().isOk());

                // 3. Add 2nd Emergency Contact
                EmergencyContactCreateDTO contact2 = new EmergencyContactCreateDTO();
                contact2.setName("Dad");
                contact2.setPrimaryContactNumber(9876543210L);

                mockMvc.perform(post("/api/student/" + studentId + "/emergency-contact")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(contact2)))
                                .andExpect(status().isOk());

                // 4. Add 3rd Emergency Contact (Should Fail)
                EmergencyContactCreateDTO contact3 = new EmergencyContactCreateDTO();
                contact3.setName("Sibling");
                contact3.setPrimaryContactNumber(5555555555L);

                mockMvc.perform(post("/api/student/" + studentId + "/emergency-contact")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(contact3)))
                                .andExpect(status().isBadRequest());
        }
}
