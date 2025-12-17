package com.project.resourcivo.dto;

import java.util.List;
import com.project.resourcivo.model.Subject;
import com.project.resourcivo.model.Faculty;
import com.project.resourcivo.model.Student;

public class CourseCreateDTO {
    private String name;
    private String code;
    private List<Subject> subjects;
    private Faculty headOfDepartment;
    private List<Student> studentsEnrolled;

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

    public Faculty getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(Faculty headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }
}
