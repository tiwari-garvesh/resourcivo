package com.project.resourcivo.mapper;

import com.project.resourcivo.model.LabEquipment;
import com.project.resourcivo.dto.LabEquipmentCreateDTO;
import com.project.resourcivo.dto.LabEquipmentUpdateDTO;
import com.project.resourcivo.dto.LabEquipmentResponseDTO;

public final class LabEquipmentMapper {

    private LabEquipmentMapper() {}

    public static LabEquipment toEntity(LabEquipmentCreateDTO dto) {
        if (dto == null) return null;
        LabEquipment e = new LabEquipment();
        if (dto.getEquipmentName() != null) e.setEquipmentName(dto.getEquipmentName());
        if (dto.getCategory() != null) e.setCategory(dto.getCategory());
        if (dto.getPurchaseDate() != null) e.setPurchaseDate(dto.getPurchaseDate());
        if (dto.getQuantityAvailable() != null) e.setQuantityAvailable(dto.getQuantityAvailable());
        if (dto.getTotalQuantity() != null) e.setTotalQuantity(dto.getTotalQuantity());
        if (dto.getLocation() != null) e.setLocation(dto.getLocation());
        if (dto.getDescription() != null) e.setDescription(dto.getDescription());
        return e;
    }

    public static void mergeUpdate(LabEquipmentUpdateDTO dto, LabEquipment entity) {
        if (dto == null || entity == null) return;
        if (dto.getEquipmentName() != null) entity.setEquipmentName(dto.getEquipmentName());
        if (dto.getCategory() != null) entity.setCategory(dto.getCategory());
        if (dto.getPurchaseDate() != null) entity.setPurchaseDate(dto.getPurchaseDate());
        if (dto.getQuantityAvailable() != null) entity.setQuantityAvailable(dto.getQuantityAvailable());
        if (dto.getTotalQuantity() != null) entity.setTotalQuantity(dto.getTotalQuantity());
        if (dto.getLocation() != null) entity.setLocation(dto.getLocation());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
    }

    public static LabEquipmentResponseDTO toResponse(LabEquipment e) {
        if (e == null) return null;
        LabEquipmentResponseDTO r = new LabEquipmentResponseDTO();
        r.setEquipmentName(e.getEquipmentName());
        r.setCategory(e.getCategory());
        r.setPurchaseDate(e.getPurchaseDate());
        r.setQuantityAvailable(e.getQuantityAvailable());
        r.setTotalQuantity(e.getTotalQuantity());
        r.setLocation(e.getLocation());
        r.setDescription(e.getDescription());
        return r;
    }
}
