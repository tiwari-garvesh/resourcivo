package com.project.resourcivo.mapper;

import com.project.resourcivo.model.EmergencyContact;
import com.project.resourcivo.dto.EmergencyContactCreateDTO;
import com.project.resourcivo.dto.EmergencyContactUpdateDTO;
import com.project.resourcivo.dto.EmergencyContactResponseDTO;

public final class EmergencyContactMapper {

    private EmergencyContactMapper() {}

    public static EmergencyContact toEntity(EmergencyContactCreateDTO dto) {
        if (dto == null) return null;
        EmergencyContact e = new EmergencyContact();
        if (dto.getName() != null) e.setName(dto.getName());
        if (dto.getPrimaryContactNumber() != null) e.setPrimaryContactNumber(dto.getPrimaryContactNumber());
        if (dto.getAlternateContactNumber() != null) e.setAlternateContactNumber(dto.getAlternateContactNumber());
        if (dto.getEmail() != null) e.setEmail(dto.getEmail());
        // resolve relation for address by id or nested DTO
        if (dto.getAddress() != null) {
            // expecting nested DTO with id field or id property; try id resolution in service
            // placeholder: set stub or fetch managed entity in service
        }
        // resolve relation for address by id or nested DTO
        if (dto.getAddress() != null) {
            // expecting nested DTO with id field or id property; try id resolution in service
            // placeholder: set stub or fetch managed entity in service
        }
        return e;
    }

    public static void mergeUpdate(EmergencyContactUpdateDTO dto, EmergencyContact entity) {
        if (dto == null || entity == null) return;
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getPrimaryContactNumber() != null) entity.setPrimaryContactNumber(dto.getPrimaryContactNumber());
        if (dto.getAlternateContactNumber() != null) entity.setAlternateContactNumber(dto.getAlternateContactNumber());
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if (dto.getAddress() != null) {
            // update relation: service should resolve and set managed Address
        }
        if (dto.getAddress() != null) {
            // update relation: service should resolve and set managed Address
        }
    }

    public static EmergencyContactResponseDTO toResponse(EmergencyContact e) {
        if (e == null) return null;
        EmergencyContactResponseDTO r = new EmergencyContactResponseDTO();
        r.setName(e.getName());
        r.setPrimaryContactNumber(e.getPrimaryContactNumber());
        r.setAlternateContactNumber(e.getAlternateContactNumber());
        r.setEmail(e.getEmail());
        if (e.getAddress() != null) r.setAddressId(e.getAddress().getId());
        if (e.getAddress() != null) r.setAddressId(e.getAddress().getId());
        return r;
    }
}
