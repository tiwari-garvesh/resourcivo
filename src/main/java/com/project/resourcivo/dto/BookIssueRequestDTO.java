package com.project.resourcivo.dto;

import jakarta.validation.constraints.NotNull;

public class BookIssueRequestDTO {
    @NotNull
    private Long studentId;
    @NotNull
    private Long bookId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
