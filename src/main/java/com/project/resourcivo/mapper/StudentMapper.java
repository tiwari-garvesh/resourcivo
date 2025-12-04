package com.project.resourcivo.mapper;

import com.project.resourcivo.model.Student;
import com.project.resourcivo.dto.StudentCreateDTO;
import com.project.resourcivo.dto.StudentUpdateDTO;
import com.project.resourcivo.dto.StudentResponseDTO;

public final class StudentMapper {

    private StudentMapper() {
    }

    public static Student toEntity(StudentCreateDTO dto) {
        if (dto == null)
            return null;
        Student e = new Student();
        if (dto.getDateOfJoining() != null)
            e.setDateOfJoining(dto.getDateOfJoining());
        if (dto.getYearOfGraduation() != null)
            e.setYearOfGraduation(dto.getYearOfGraduation());
        // resolve relation for course by id or nested DTO
        if (dto.getCourse() != null) {
            // expecting nested DTO with id field or id property; try id resolution in
            // service
            // placeholder: set stub or fetch managed entity in service
        }
        // resolve relation for course by id or nested DTO
        if (dto.getCourse() != null) {
            // expecting nested DTO with id field or id property; try id resolution in
            // service
            // placeholder: set stub or fetch managed entity in service
        }
        return e;
    }

    public static void mergeUpdate(StudentUpdateDTO dto, Student entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getDateOfJoining() != null)
            entity.setDateOfJoining(dto.getDateOfJoining());
        if (dto.getYearOfGraduation() != null)
            entity.setYearOfGraduation(dto.getYearOfGraduation());
        if (dto.getCourse() != null) {
            // update relation: service should resolve and set managed Course
        }
        if (dto.getCourse() != null) {
            // update relation: service should resolve and set managed Course
        }
    }

    public static StudentResponseDTO toResponse(Student e) {
        if (e == null)
            return null;
        StudentResponseDTO r = new StudentResponseDTO();
        r.setDateOfJoining(e.getDateOfJoining());
        r.setYearOfGraduation(e.getYearOfGraduation());
        // Note: course is String in model but CourseRefDTO in DTO - skipping mapping
        return r;
    }
}
