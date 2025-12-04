package com.project.resourcivo.mapper;

import com.project.resourcivo.model.Course;
import com.project.resourcivo.dto.CourseCreateDTO;
import com.project.resourcivo.dto.CourseUpdateDTO;
import com.project.resourcivo.dto.CourseResponseDTO;

public final class CourseMapper {

    private CourseMapper() {
    }

    public static Course toEntity(CourseCreateDTO dto) {
        if (dto == null)
            return null;
        Course e = new Course();
        if (dto.getName() != null)
            e.setName(dto.getName());
        if (dto.getCode() != null)
            e.setCode(dto.getCode());
        if (dto.getSubjects() != null)
            e.setSubjects(dto.getSubjects());
        if (dto.getSubjects() != null)
            e.setSubjects(dto.getSubjects());
        // resolve relation for headOfDepartment by id or nested DTO
        if (dto.getHeadOfDepartment() != null) {
            // expecting nested DTO with id field or id property; try id resolution in
            // service
            // placeholder: set stub or fetch managed entity in service
        }
        // resolve relation for headOfDepartment by id or nested DTO
        if (dto.getHeadOfDepartment() != null) {
            // expecting nested DTO with id field or id property; try id resolution in
            // service
            // placeholder: set stub or fetch managed entity in service
        }
        if (dto.getStudentsEnrolled() != null)
            e.setStudentsEnrolled(dto.getStudentsEnrolled());
        if (dto.getStudentsEnrolled() != null)
            e.setStudentsEnrolled(dto.getStudentsEnrolled());
        return e;
    }

    public static void mergeUpdate(CourseUpdateDTO dto, Course entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getName() != null)
            entity.setName(dto.getName());
        if (dto.getCode() != null)
            entity.setCode(dto.getCode());
        if (dto.getSubjects() != null)
            entity.setSubjects(dto.getSubjects());
        if (dto.getSubjects() != null)
            entity.setSubjects(dto.getSubjects());
        if (dto.getHeadOfDepartment() != null) {
            // update relation: service should resolve and set managed Faculty
        }
        if (dto.getHeadOfDepartment() != null) {
            // update relation: service should resolve and set managed Faculty
        }
        if (dto.getStudentsEnrolled() != null)
            entity.setStudentsEnrolled(dto.getStudentsEnrolled());
        if (dto.getStudentsEnrolled() != null)
            entity.setStudentsEnrolled(dto.getStudentsEnrolled());
    }

    public static CourseResponseDTO toResponse(Course e) {
        if (e == null)
            return null;
        CourseResponseDTO r = new CourseResponseDTO();
        r.setName(e.getName());
        r.setCode(e.getCode());
        r.setSubjects(e.getSubjects());
        if (e.getHeadOfDepartment() != null)
            r.setHeadOfDepartmentId(e.getHeadOfDepartment().getId());
        r.setStudentsEnrolled(e.getStudentsEnrolled());
        return r;
    }
}
