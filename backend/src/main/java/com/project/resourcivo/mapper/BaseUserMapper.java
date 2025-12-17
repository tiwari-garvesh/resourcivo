package com.project.resourcivo.mapper;

import com.project.resourcivo.model.BaseUser;
import com.project.resourcivo.dto.BaseUserCreateDTO;
import com.project.resourcivo.dto.BaseUserUpdateDTO;
import com.project.resourcivo.dto.BaseUserResponseDTO;

public final class BaseUserMapper {

    private BaseUserMapper() {}

    public static BaseUser toEntity(BaseUserCreateDTO dto) {
        if (dto == null) return null;
        BaseUser e = new BaseUser();
        if (dto.getFirstName() != null) e.setFirstName(dto.getFirstName());
        if (dto.getMiddleName() != null) e.setMiddleName(dto.getMiddleName());
        if (dto.getLastName() != null) e.setLastName(dto.getLastName());
        if (dto.getDateOfBirth() != null) e.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getGender() != null) e.setGender(dto.getGender());
        // resolve relation for currentAddress by id or nested DTO
        if (dto.getCurrentAddress() != null) {
            // expecting nested DTO with id field or id property; try id resolution in service
            // placeholder: set stub or fetch managed entity in service
        }
        // resolve relation for currentAddress by id or nested DTO
        if (dto.getCurrentAddress() != null) {
            // expecting nested DTO with id field or id property; try id resolution in service
            // placeholder: set stub or fetch managed entity in service
        }
        // resolve relation for permanentAddress by id or nested DTO
        if (dto.getPermanentAddress() != null) {
            // expecting nested DTO with id field or id property; try id resolution in service
            // placeholder: set stub or fetch managed entity in service
        }
        // resolve relation for permanentAddress by id or nested DTO
        if (dto.getPermanentAddress() != null) {
            // expecting nested DTO with id field or id property; try id resolution in service
            // placeholder: set stub or fetch managed entity in service
        }
        // resolve relation for contact by id or nested DTO
        if (dto.getContact() != null) {
            // expecting nested DTO with id field or id property; try id resolution in service
            // placeholder: set stub or fetch managed entity in service
        }
        // resolve relation for contact by id or nested DTO
        if (dto.getContact() != null) {
            // expecting nested DTO with id field or id property; try id resolution in service
            // placeholder: set stub or fetch managed entity in service
        }
        if (dto.getEmergencyContact() != null) e.setEmergencyContact(dto.getEmergencyContact());
        if (dto.getEmergencyContact() != null) e.setEmergencyContact(dto.getEmergencyContact());
        if (dto.getBio() != null) e.setBio(dto.getBio());
        return e;
    }

    public static void mergeUpdate(BaseUserUpdateDTO dto, BaseUser entity) {
        if (dto == null || entity == null) return;
        if (dto.getFirstName() != null) entity.setFirstName(dto.getFirstName());
        if (dto.getMiddleName() != null) entity.setMiddleName(dto.getMiddleName());
        if (dto.getLastName() != null) entity.setLastName(dto.getLastName());
        if (dto.getDateOfBirth() != null) entity.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getGender() != null) entity.setGender(dto.getGender());
        if (dto.getCurrentAddress() != null) {
            // update relation: service should resolve and set managed Address
        }
        if (dto.getCurrentAddress() != null) {
            // update relation: service should resolve and set managed Address
        }
        if (dto.getPermanentAddress() != null) {
            // update relation: service should resolve and set managed Address
        }
        if (dto.getPermanentAddress() != null) {
            // update relation: service should resolve and set managed Address
        }
        if (dto.getContact() != null) {
            // update relation: service should resolve and set managed Contact
        }
        if (dto.getContact() != null) {
            // update relation: service should resolve and set managed Contact
        }
        if (dto.getEmergencyContact() != null) entity.setEmergencyContact(dto.getEmergencyContact());
        if (dto.getEmergencyContact() != null) entity.setEmergencyContact(dto.getEmergencyContact());
        if (dto.getBio() != null) entity.setBio(dto.getBio());
    }

    public static BaseUserResponseDTO toResponse(BaseUser e) {
        if (e == null) return null;
        BaseUserResponseDTO r = new BaseUserResponseDTO();
        r.setFirstName(e.getFirstName());
        r.setMiddleName(e.getMiddleName());
        r.setLastName(e.getLastName());
        r.setDateOfBirth(e.getDateOfBirth());
        r.setGender(e.getGender());
        if (e.getCurrentAddress() != null) r.setCurrentAddressId(e.getCurrentAddress().getId());
        if (e.getCurrentAddress() != null) r.setCurrentAddressId(e.getCurrentAddress().getId());
        if (e.getPermanentAddress() != null) r.setPermanentAddressId(e.getPermanentAddress().getId());
        if (e.getPermanentAddress() != null) r.setPermanentAddressId(e.getPermanentAddress().getId());
        if (e.getContact() != null) r.setContactId(e.getContact().getId());
        if (e.getContact() != null) r.setContactId(e.getContact().getId());
        r.setEmergencyContact(e.getEmergencyContact());
        r.setEmergencyContact(e.getEmergencyContact());
        r.setBio(e.getBio());
        return r;
    }
}
