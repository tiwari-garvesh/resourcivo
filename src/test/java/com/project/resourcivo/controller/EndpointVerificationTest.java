package com.project.resourcivo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointVerificationTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @WithMockUser(username = "admin", roles = { "ADMIN" })
        public void verificationMap() throws Exception {
                String emptyJson = "{}";

                // Address (Legacy: POST /search)
                mockMvc.perform(post("/api/address/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isOk());

                // Classroom (Legacy: POST /search)
                mockMvc.perform(post("/api/classroom/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isOk());

                // Classroom (New: GET /)
                mockMvc.perform(get("/api/classroom"))
                                .andExpect(status().isOk());

                // Classroom (New: DELETE /)
                mockMvc.perform(delete("/api/classroom/99999"))
                                .andExpect(status().isNoContent());

                // Contact (Legacy: POST /search)
                mockMvc.perform(post("/api/contact/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isOk());

                // Course (Legacy: POST /search)
                mockMvc.perform(post("/api/course/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isOk());

                // Course (New: DELETE /)
                mockMvc.perform(delete("/api/course/99999"))
                                .andExpect(status().isNoContent());

                // Course (New: GET /students)
                // Using a non-existent course ID to expect 404, or mocked data if possible.
                // Since we use repo in actual code, and this is an integration test with H2,
                // we can just check if endpoint is reachable (404 for 99999).
                mockMvc.perform(get("/api/course/99999/students"))
                                .andExpect(status().isNotFound());

                // Emergency Contact (Legacy: POST /search)
                mockMvc.perform(post("/api/emergencycontact/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isOk());

                // Emergency Contact (New: DELETE /)
                mockMvc.perform(delete("/api/emergencycontact/99999"))
                                .andExpect(status().isNoContent());

                // Faculty (Legacy: POST /search)
                mockMvc.perform(post("/api/faculty/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isOk());

                // Inventory (Skipping: Empty Controller)
                // mockMvc.perform(get("/inventory")).andExpect(status().isNotFound());

                // Inventory Item (Legacy: POST /search)
                mockMvc.perform(post("/api/inventory-item/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isOk());

                // Lab Equipment (Legacy: POST /search)
                mockMvc.perform(post("/api/lab-equipment/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isOk());

                // Librarian (New: GET /)
                mockMvc.perform(get("/api/librarian")).andExpect(status().isOk());

                // Librarian (New: DELETE /)
                mockMvc.perform(delete("/api/librarian/99999"))
                                .andExpect(status().isNoContent());

                // Library Book (Legacy Pattern: POST /search)
                mockMvc.perform(post("/api/librarybook/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isOk());

                // Library Book (New: DELETE /)
                mockMvc.perform(delete("/api/librarybook/99999"))
                                .andExpect(status().isNoContent());

                // Library Book (New: GET /)
                mockMvc.perform(get("/api/librarybook"))
                                .andExpect(status().isOk());

                // Library (New: GET /)
                mockMvc.perform(get("/api/library")).andExpect(status().isOk());

                // Student (Legacy Pattern: POST /search)
                mockMvc.perform(post("/api/student/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isOk());

                // Student (New: DELETE /)
                mockMvc.perform(delete("/api/student/99999"))
                                .andExpect(status().isNoContent());

                // Student (New: GET /)
                mockMvc.perform(get("/api/student"))
                                .andExpect(status().isOk());

                // Subject (Legacy: POST /search)
                mockMvc.perform(post("/api/subject/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isOk());

                // Subject (New: DELETE /)
                mockMvc.perform(delete("/api/subject/99999"))
                                .andExpect(status().isNoContent());

                // Subject (New: GET /)
                mockMvc.perform(get("/api/subject"))
                                .andExpect(status().isOk());

                // Transport (Legacy: POST /search)
                mockMvc.perform(post("/api/transport/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isOk());

                // Auth
                // Login (Empty -> 401 or 400 depending on validation, expecting 400 due to
                // @Valid)
                mockMvc.perform(post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isBadRequest());

                // Register (Empty -> 400)
                mockMvc.perform(post("/api/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(emptyJson))
                                .andExpect(status().isBadRequest());

                // Me (Authenticated via @WithMockUser -> 200 or 401/404 if user lookup fails)
                mockMvc.perform(get("/api/auth/me"))
                                .andExpect(result -> {
                                        int status = result.getResponse().getStatus();
                                        // If 404, it might be ResourceNotFoundException (handled) or Endpoint Missing.
                                        // Custom error response contains "message".
                                        if (status == 404) {
                                                String content = result.getResponse().getContentAsString();
                                                if (!content.contains("message")) {
                                                        // If plain 404 without my custom body, likely endpoint missing
                                                        throw new AssertionError(
                                                                        "Endpoint /api/auth/me returned 404 without standard error body - might be missing.");
                                                }
                                        }
                                        // If not 404, we assume endpoint exists (200, 401, 500, etc.)
                                });
        }
}
