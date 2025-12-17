package com.project.resourcivo.mapper;

import com.project.resourcivo.dto.LabEquipmentCreateDTO;
import com.project.resourcivo.dto.LabEquipmentResponseDTO;
import com.project.resourcivo.model.LabEquipment;
import com.project.resourcivo.model.EquipmentStatus;

public class LabEquipmentMapper {

    public static LabEquipment toEntity(LabEquipmentCreateDTO dto) {
        if (dto == null)
            return null;
        LabEquipment e = new LabEquipment();
        e.setStatus(EquipmentStatus.OPERATIONAL); // Default
        updateEntity(dto, e);
        return e;
    }

    public static void updateEntity(LabEquipmentCreateDTO dto, LabEquipment e) {
        if (dto == null || e == null)
            return;
        if (dto.getName() != null)
            e.setName(dto.getName());
        if (dto.getLabType() != null)
            e.setLabType(dto.getLabType());
        if (dto.getCategory() != null)
            e.setCategory(dto.getCategory());
        if (dto.getTotalQuantity() != null)
            e.setTotalQuantity(dto.getTotalQuantity());
        if (dto.getAvailableQuantity() != null)
            e.setAvailableQuantity(dto.getAvailableQuantity());
        if (dto.getStatus() != null)
            e.setStatus(dto.getStatus());
        if (dto.getConditionDetails() != null)
            e.setConditionDetails(dto.getConditionDetails());
        if (dto.getModelNumber() != null)
            e.setModelNumber(dto.getModelNumber());
        if (dto.getDescription() != null)
            e.setDescription(dto.getDescription());
        if (dto.getLastMaintenanceDate() != null)
            e.setLastMaintenanceDate(dto.getLastMaintenanceDate());
        if (dto.getNextMaintenanceDueDate() != null)
            e.setNextMaintenanceDueDate(dto.getNextMaintenanceDueDate());
        if (dto.getAisleNumber() != null)
            e.setAisleNumber(dto.getAisleNumber());
        if (dto.getShelfNumber() != null)
            e.setShelfNumber(dto.getShelfNumber());
    }

    public static LabEquipmentResponseDTO toResponse(LabEquipment e) {
        if (e == null)
            return null;
        LabEquipmentResponseDTO r = new LabEquipmentResponseDTO();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setLabType(e.getLabType());
        r.setCategory(e.getCategory());
        r.setTotalQuantity(e.getTotalQuantity());
        r.setAvailableQuantity(e.getAvailableQuantity());
        r.setStatus(e.getStatus());
        r.setConditionDetails(e.getConditionDetails());
        r.setModelNumber(e.getModelNumber());
        r.setDescription(e.getDescription());
        r.setLastMaintenanceDate(e.getLastMaintenanceDate());
        r.setNextMaintenanceDueDate(e.getNextMaintenanceDueDate());
        r.setAisleNumber(e.getAisleNumber());
        r.setShelfNumber(e.getShelfNumber());
        return r;
    }
}
