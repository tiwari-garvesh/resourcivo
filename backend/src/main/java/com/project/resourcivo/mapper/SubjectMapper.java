package com.project.resourcivo.mapper;

import com.project.resourcivo.model.Subject;
import com.project.resourcivo.dto.SubjectCreateDTO;
import com.project.resourcivo.dto.SubjectUpdateDTO;
import com.project.resourcivo.dto.SubjectResponseDTO;

public final class SubjectMapper {

    private SubjectMapper() {
    }

    public static Subject toEntity(SubjectCreateDTO dto) {
        if (dto == null)
            return null;
        Subject e = new Subject();
        updateEntity(dto, e);
        return e;
    }

    public static void updateEntity(SubjectCreateDTO dto, Subject entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getName() != null)
            entity.setName(dto.getName());
        if (dto.getCode() != null)
            entity.setCode(dto.getCode());
        if (dto.getRefBooks() != null)
            entity.setRefBooks(dto.getRefBooks());
        if (dto.getTaughtBy() != null)
            entity.setTaughtBy(dto.getTaughtBy());
        if (dto.getStudiedBy() != null)
            entity.setStudiedBy(dto.getStudiedBy());
    }

    public static void mergeUpdate(SubjectUpdateDTO dto, Subject entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getName() != null)
            entity.setName(dto.getName());
        if (dto.getCode() != null)
            entity.setCode(dto.getCode());
        if (dto.getRefBooks() != null)
            entity.setRefBooks(dto.getRefBooks());
        if (dto.getRefBooks() != null)
            entity.setRefBooks(dto.getRefBooks());
        if (dto.getTaughtBy() != null)
            entity.setTaughtBy(dto.getTaughtBy());
        if (dto.getTaughtBy() != null)
            entity.setTaughtBy(dto.getTaughtBy());
        if (dto.getStudiedBy() != null)
            if (dto.getTaughtBy() != null)
                entity.setTaughtBy(dto.getTaughtBy());
        if (dto.getStudiedBy() != null)
            entity.setStudiedBy(dto.getStudiedBy());
    }

    public static SubjectResponseDTO toResponse(Subject e) {
        if (e == null)
            return null;
        SubjectResponseDTO r = new SubjectResponseDTO();
        r.setName(e.getName());
        r.setCode(e.getCode());
        r.setRefBooks(e.getRefBooks());
        r.setTaughtBy(e.getTaughtBy());
        r.setStudiedBy(e.getStudiedBy());
        return r;
    }
}
