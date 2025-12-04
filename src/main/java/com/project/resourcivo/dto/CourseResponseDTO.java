package com.project.resourcivo.dto;

import java.util.List;
import com.project.resourcivo.model.Subject;
import com.project.resourcivo.model.Student;

public class CourseResponseDTO {
    private Long id;
    private String name;
    private String code;
    private List<Subject> subjects;
    private Long headOfDepartmentId;
    private List<Student> studentsEnrolled;

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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Long getHeadOfDepartmentId() {
        return headOfDepartmentId;
    }

    public void setHeadOfDepartmentId(Long headOfDepartmentId) {
        this.headOfDepartmentId = headOfDepartmentId;
    }

    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }
}
