package com.project.resourcivo.mapper;

import com.project.resourcivo.dto.TransportManagerCreateDTO;
import com.project.resourcivo.dto.TransportManagerResponseDTO;
import com.project.resourcivo.dto.TransportManagerUpdateDTO;
import com.project.resourcivo.model.TransportManager;
import org.springframework.stereotype.Component;

@Component
public class TransportManagerMapper {

    public TransportManager toEntity(TransportManagerCreateDTO dto) {
        if (dto == null)
            return null;
        TransportManager entity = new TransportManager();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setJoiningDate(dto.getJoiningDate());
        return entity;
    }

    public TransportManagerResponseDTO toResponse(TransportManager entity) {
        if (entity == null)
            return null;
        TransportManagerResponseDTO dto = new TransportManagerResponseDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setJoiningDate(entity.getJoiningDate());
        return dto;
    }

    public void updateEntityFromDto(TransportManager entity, TransportManagerUpdateDTO dto) {
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
}
