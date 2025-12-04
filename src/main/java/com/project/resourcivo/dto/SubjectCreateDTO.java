package com.project.resourcivo.dto;

import java.util.List;
import com.project.resourcivo.model.LibraryBook;
import com.project.resourcivo.model.Faculty;
import com.project.resourcivo.model.Student;
import jakarta.validation.constraints.*;

public class SubjectCreateDTO {
    @NotBlank(message = "Subject name is required")
    @Size(min = 2, max = 200, message = "Subject name must be between 2 and 200 characters")
    private String name;

    @NotBlank(message = "Subject code is required")
    @Size(min = 2, max = 20, message = "Subject code must be between 2 and 20 characters")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Subject code must contain only uppercase letters and numbers")
    private String code;

    private List<LibraryBook> refBooks;
    private List<Faculty> taughtBy;
    private List<Student> studiedBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<LibraryBook> getRefBooks() {
        return refBooks;
    }

    public void setRefBooks(List<LibraryBook> refBooks) {
        this.refBooks = refBooks;
    }

    public List<Faculty> getTaughtBy() {
        return taughtBy;
    }

    public void setTaughtBy(List<Faculty> taughtBy) {
        this.taughtBy = taughtBy;
    }

    public List<Student> getStudiedBy() {
        return studiedBy;
    }

    public void setStudiedBy(List<Student> studiedBy) {
        this.studiedBy = studiedBy;
    }
}
