package com.project.resourcivo.mapper;

import com.project.resourcivo.model.Librarian;
import com.project.resourcivo.dto.LibrarianCreateDTO;
import com.project.resourcivo.dto.LibrarianUpdateDTO;
import com.project.resourcivo.dto.LibrarianResponseDTO;

public final class LibrarianMapper {

    private LibrarianMapper() {
    }

    public static Librarian toEntity(LibrarianCreateDTO dto) {
        if (dto == null)
            return null;
        Librarian e = new Librarian();
        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setEmail(dto.getEmail());
        e.setPhoneNumber(dto.getPhoneNumber());
        e.setJoiningDate(dto.getJoiningDate());
        return e;
    }

    public static void mergeUpdate(LibrarianUpdateDTO dto, Librarian entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getFirstName() != null)
            entity.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null)
            entity.setLastName(dto.getLastName());
        if (dto.getEmail() != null)
            entity.setEmail(dto.getEmail());
        if (dto.getPhoneNumber() != null)
            entity.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getJoiningDate() != null)
            entity.setJoiningDate(dto.getJoiningDate());
    }

    public static LibrarianResponseDTO toResponse(Librarian e) {
        if (e == null)
            return null;
        LibrarianResponseDTO r = new LibrarianResponseDTO();
        r.setId(e.getId());
        r.setFirstName(e.getFirstName());
        r.setLastName(e.getLastName());
        r.setEmail(e.getEmail());
        r.setPhoneNumber(e.getPhoneNumber());
        r.setJoiningDate(e.getJoiningDate());
        if (e.getLibrary() != null) {
            r.setLibraryId(e.getLibrary().getId());
        }
        return r;
    }
}
