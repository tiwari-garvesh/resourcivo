package com.project.resourcivo.service;

import java.util.List;
import com.project.resourcivo.dto.LabEquipmentCreateDTO;
import com.project.resourcivo.dto.LabEquipmentUpdateDTO;
import com.project.resourcivo.dto.LabEquipmentResponseDTO;
import com.project.resourcivo.criteria.LabEquipmentFilterDTO;

public interface ILabEquipmentService {
    LabEquipmentResponseDTO createFromDto(LabEquipmentCreateDTO dto);
    LabEquipmentResponseDTO updateFromDto(Long id, LabEquipmentCreateDTO dto);
    LabEquipmentResponseDTO partialUpdateFromDto(Long id, LabEquipmentUpdateDTO dto);
    java.util.List<LabEquipmentResponseDTO> search(LabEquipmentFilterDTO filter);
}
