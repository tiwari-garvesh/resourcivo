package com.project.resourcivo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.resourcivo.dto.*;
// import com.project.resourcivo.model.IssueStatus;
// import com.project.resourcivo.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
// import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = { "ADMIN", "LIBRARIAN" })
public class HistoryVerificationTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void verifyHistoryFeatures() throws Exception {
                // 1. Setup Data
                // Create Student
                StudentCreateDTO studentDto = new StudentCreateDTO();
                studentDto.setFirstName("History");
                studentDto.setLastName("Student");
                studentDto.setDateOfBirth(LocalDate.of(2000, 1, 1));
                studentDto.setGender("Male");

                ContactCreateDTO contactDto = new ContactCreateDTO();
                contactDto.setPrimaryEmail("hist@student.com");
                contactDto.setPrimaryNumber(1234567890L);
                studentDto.setContact(contactDto);

                MvcResult studentResult = mockMvc.perform(post("/api/student")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(studentDto)))
                                .andExpect(status().isCreated())
                                .andReturn();
                StudentResponseDTO student = objectMapper.readValue(studentResult.getResponse().getContentAsString(),
                                StudentResponseDTO.class);

                // Create Library
                LibraryCreateDTO libDto = new LibraryCreateDTO();
                libDto.setName("History Lib");
                MvcResult libResult = mockMvc.perform(post("/api/library")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(libDto)))
                                .andExpect(status().isCreated())
                                .andReturn();
                LibraryResponseDTO library = objectMapper.readValue(libResult.getResponse().getContentAsString(),
                                LibraryResponseDTO.class);

                // Create Book
                LibraryBookCreateDTO bookDto = new LibraryBookCreateDTO();
                bookDto.setName("History Book");
                bookDto.setLibraryId(library.getId());

                MvcResult bookResult = mockMvc.perform(post("/api/librarybook")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bookDto)))
                                .andExpect(status().isCreated())
                                .andReturn();
                LibraryBookResponseDTO book = objectMapper.readValue(bookResult.getResponse().getContentAsString(),
                                LibraryBookResponseDTO.class);

                // 2. Request Issue & Approve
                BookIssueRequestDTO issueReq = new BookIssueRequestDTO();
                issueReq.setStudentId(student.getId());
                issueReq.setBookId(book.getId());

                MvcResult reqResult = mockMvc.perform(post("/api/student/issue-request")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(issueReq)))
                                .andExpect(status().isCreated())
                                .andReturn();
                BookIssueResponseDTO issue = objectMapper.readValue(reqResult.getResponse().getContentAsString(),
                                BookIssueResponseDTO.class);

                mockMvc.perform(put("/api/librarian/issue-request/" + issue.getId() + "/approve"))
                                .andExpect(status().isOk());

                // 3. Verify Issued Books (Should contain 1)
                mockMvc.perform(get("/api/student/" + student.getId() + "/issued-books"))
                                .andExpect(status().isOk())
                                .andExpect(result -> {
                                        String content = result.getResponse().getContentAsString();
                                        assertTrue(content.contains("History Book"));
                                });

                // 4. Verify Issue History (Should contain 1)
                mockMvc.perform(get("/api/student/" + student.getId() + "/issue-history"))
                                .andExpect(status().isOk())
                                .andExpect(result -> {
                                        String content = result.getResponse().getContentAsString();
                                        assertTrue(content.contains("History Book"));
                                });

                // 5. Verify Book History (Should contain student)
                mockMvc.perform(get("/api/librarybook/" + book.getId() + "/history"))
                                .andExpect(status().isOk())
                                .andExpect(result -> {
                                        String content = result.getResponse().getContentAsString();
                                        assertTrue(content.contains("History Student"));
                                });

                // 6. Request Return
                mockMvc.perform(put("/api/student/issue-request/" + issue.getId() + "/return"))
                                .andExpect(status().isOk());

                // 7. Verify Issued Books (Should STILL contain 1 because it's pending return)
                mockMvc.perform(get("/api/student/" + student.getId() + "/issued-books"))
                                .andExpect(status().isOk())
                                .andExpect(result -> {
                                        String content = result.getResponse().getContentAsString();
                                        assertTrue(content.contains("History Book"));
                                });

                // 8. Confirm Return
                mockMvc.perform(put("/api/librarian/issue-request/" + issue.getId() + "/confirm-return"))
                                .andExpect(status().isOk());

                // 9. Verify Issued Books (Should be EMPTY now)
                mockMvc.perform(get("/api/student/" + student.getId() + "/issued-books"))
                                .andExpect(status().isOk())
                                .andExpect(result -> {
                                        String content = result.getResponse().getContentAsString();
                                        assertEquals("[]", content);
                                });

                // 10. Verify Issue History (Should contain 1 with RETURNED status)
                mockMvc.perform(get("/api/student/" + student.getId() + "/issue-history"))
                                .andExpect(status().isOk())
                                .andExpect(result -> {
                                        String content = result.getResponse().getContentAsString();
                                        assertTrue(content.contains("RETURNED"));
                                });
        }
}
