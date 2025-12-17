package com.project.resourcivo.mapper;

import com.project.resourcivo.model.Contact;
import com.project.resourcivo.dto.ContactCreateDTO;
import com.project.resourcivo.dto.ContactUpdateDTO;
import com.project.resourcivo.dto.ContactResponseDTO;

public final class ContactMapper {

    private ContactMapper() {}

    public static Contact toEntity(ContactCreateDTO dto) {
        if (dto == null) return null;
        Contact e = new Contact();
        if (dto.getPrimaryNumber() != null) e.setPrimaryNumber(dto.getPrimaryNumber());
        if (dto.getAlternateNumber() != null) e.setAlternateNumber(dto.getAlternateNumber());
        if (dto.getPrimaryEmail() != null) e.setPrimaryEmail(dto.getPrimaryEmail());
        if (dto.getAlternateEmail() != null) e.setAlternateEmail(dto.getAlternateEmail());
        return e;
    }

    public static void mergeUpdate(ContactUpdateDTO dto, Contact entity) {
        if (dto == null || entity == null) return;
        if (dto.getPrimaryNumber() != null) entity.setPrimaryNumber(dto.getPrimaryNumber());
        if (dto.getAlternateNumber() != null) entity.setAlternateNumber(dto.getAlternateNumber());
        if (dto.getPrimaryEmail() != null) entity.setPrimaryEmail(dto.getPrimaryEmail());
        if (dto.getAlternateEmail() != null) entity.setAlternateEmail(dto.getAlternateEmail());
    }

    public static ContactResponseDTO toResponse(Contact e) {
        if (e == null) return null;
        ContactResponseDTO r = new ContactResponseDTO();
        r.setPrimaryNumber(e.getPrimaryNumber());
        r.setAlternateNumber(e.getAlternateNumber());
        r.setPrimaryEmail(e.getPrimaryEmail());
        r.setAlternateEmail(e.getAlternateEmail());
        return r;
    }
}
