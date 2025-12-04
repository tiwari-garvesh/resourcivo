package com.project.resourcivo.mapper;

import com.project.resourcivo.model.Faculty;
import com.project.resourcivo.dto.FacultyCreateDTO;
import com.project.resourcivo.dto.FacultyUpdateDTO;
import com.project.resourcivo.dto.FacultyResponseDTO;

public final class FacultyMapper {

    private FacultyMapper() {
    }

    public static Faculty toEntity(FacultyCreateDTO dto) {
        if (dto == null)
            return null;
        Faculty e = new Faculty();
        if (dto.getDateOfJoining() != null)
            e.setDateOfJoining(dto.getDateOfJoining());
        if (dto.getLevel() != null)
            e.setLevel(dto.getLevel());
        // resolve relation for subject by id or nested DTO
        if (dto.getSubject() != null) {
            // expecting nested DTO with id field or id property; try id resolution in
            // service
            // placeholder: set stub or fetch managed entity in service
        }
        // resolve relation for subject by id or nested DTO
        if (dto.getSubject() != null) {
            // expecting nested DTO with id field or id property; try id resolution in
            // service
            // placeholder: set stub or fetch managed entity in service
        }
        if (dto.getYearOfExperience() != null)
            e.setYearOfExperience(dto.getYearOfExperience());
        return e;
    }

    public static void mergeUpdate(FacultyUpdateDTO dto, Faculty entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getDateOfJoining() != null)
            entity.setDateOfJoining(dto.getDateOfJoining());
        if (dto.getLevel() != null)
            entity.setLevel(dto.getLevel());
        if (dto.getSubject() != null) {
            // update relation: service should resolve and set managed Subject
        }
        if (dto.getSubject() != null) {
            // update relation: service should resolve and set managed Subject
        }
        if (dto.getYearOfExperience() != null)
            entity.setYearOfExperience(dto.getYearOfExperience());
    }

    public static FacultyResponseDTO toResponse(Faculty e) {
        if (e == null)
            return null;
        FacultyResponseDTO r = new FacultyResponseDTO();
        r.setDateOfJoining(e.getDateOfJoining());
        r.setLevel(e.getLevel());
        // Note: subject is String in model but SubjectRefDTO in DTO - skipping mapping
        r.setYearOfExperience(e.getYearOfExperience());
        return r;
    }
}
