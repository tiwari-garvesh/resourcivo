package com.project.resourcivo.dto;

import java.util.List;
import com.project.resourcivo.model.LibraryBook;
import com.project.resourcivo.model.Faculty;
import com.project.resourcivo.model.Student;

public class SubjectResponseDTO {
    private Long id;
    private String name;
    private String code;
    private List<LibraryBook> refBooks;
    private List<Faculty> taughtBy;
    private List<Student> studiedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
