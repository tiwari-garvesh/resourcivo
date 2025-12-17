package com.project.resourcivo.mapper;

import com.project.resourcivo.model.Library;
import com.project.resourcivo.dto.LibraryCreateDTO;
import com.project.resourcivo.dto.LibraryUpdateDTO;
import com.project.resourcivo.dto.LibraryResponseDTO;

public final class LibraryMapper {

    private LibraryMapper() {
    }

    public static Library toEntity(LibraryCreateDTO dto) {
        if (dto == null)
            return null;
        Library e = new Library();
        e.setName(dto.getName());
        e.setLocation(dto.getLocation());
        return e;
    }

    public static void mergeUpdate(LibraryUpdateDTO dto, Library entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getName() != null)
            entity.setName(dto.getName());
        if (dto.getLocation() != null)
            entity.setLocation(dto.getLocation());
    }

    public static LibraryResponseDTO toResponse(Library e) {
        if (e == null)
            return null;
        LibraryResponseDTO r = new LibraryResponseDTO();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setLocation(e.getLocation());
        return r;
    }
}
