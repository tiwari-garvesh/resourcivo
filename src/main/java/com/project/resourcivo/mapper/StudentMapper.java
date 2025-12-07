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
        updateEntity(dto, e);
        return e;
    }

    public static void updateEntity(StudentCreateDTO dto, Student entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getFirstName() != null)
            entity.setFirstName(dto.getFirstName());
        if (dto.getMiddleName() != null)
            entity.setMiddleName(dto.getMiddleName());
        if (dto.getLastName() != null)
            entity.setLastName(dto.getLastName());
        if (dto.getDateOfBirth() != null)
            entity.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getBio() != null)
            entity.setBio(dto.getBio());
        if (dto.getDateOfJoining() != null)
            entity.setDateOfJoining(dto.getDateOfJoining());
        if (dto.getYearOfGraduation() != null)
            entity.setYearOfGraduation(dto.getYearOfGraduation());

        // Handle Contact
        if (dto.getContact() != null) {
            entity.setContact(ContactMapper.toEntity(dto.getContact()));
        }

        // Handle Address
        if (dto.getCurrentAddress() != null) {
            entity.setCurrentAddress(AddressMapper.toEntity(dto.getCurrentAddress()));
        }
        if (dto.getPermanentAddress() != null) {
            entity.setPermanentAddress(AddressMapper.toEntity(dto.getPermanentAddress()));
        }

        // Handle Course - Assuming storing Course Name or ID as String for now
        if (dto.getCourse() != null) {
            entity.setCourse(dto.getCourse().getName());
        }
    }

    public static void mergeUpdate(StudentUpdateDTO dto, Student entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getFirstName() != null)
            entity.setFirstName(dto.getFirstName());
        if (dto.getMiddleName() != null)
            entity.setMiddleName(dto.getMiddleName());
        if (dto.getLastName() != null)
            entity.setLastName(dto.getLastName());
        if (dto.getDateOfBirth() != null)
            entity.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getBio() != null)
            entity.setBio(dto.getBio());
        if (dto.getDateOfJoining() != null)
            entity.setDateOfJoining(dto.getDateOfJoining());
        if (dto.getYearOfGraduation() != null)
            entity.setYearOfGraduation(dto.getYearOfGraduation());
        // Course update logic omitted for brevity or needs specific handling
    }

    public static StudentResponseDTO toResponse(Student e) {
        if (e == null)
            return null;
        StudentResponseDTO r = new StudentResponseDTO();
        r.setId(e.getId());
        r.setFirstName(e.getFirstName());
        r.setMiddleName(e.getMiddleName());
        r.setLastName(e.getLastName());
        r.setDateOfBirth(e.getDateOfBirth());
        r.setBio(e.getBio());
        r.setDateOfJoining(e.getDateOfJoining());
        r.setYearOfGraduation(e.getYearOfGraduation());
        // Map Relationships as IDs
        if (e.getContact() != null) {
            r.setContactId(e.getContact().getId());
        }
        if (e.getCurrentAddress() != null) {
            r.setCurrentAddressId(e.getCurrentAddress().getId());
        }
        if (e.getPermanentAddress() != null) {
            r.setPermanentAddressId(e.getPermanentAddress().getId());
        }
        return r;
    }
}
