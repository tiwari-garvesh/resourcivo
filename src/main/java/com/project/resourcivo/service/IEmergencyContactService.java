package com.project.resourcivo.service;

import java.util.List;
import com.project.resourcivo.dto.EmergencyContactCreateDTO;
import com.project.resourcivo.dto.EmergencyContactUpdateDTO;
import com.project.resourcivo.dto.EmergencyContactResponseDTO;
import com.project.resourcivo.criteria.EmergencyContactFilterDTO;

public interface IEmergencyContactService {
    EmergencyContactResponseDTO createFromDto(EmergencyContactCreateDTO dto);

    EmergencyContactResponseDTO updateFromDto(Long id, EmergencyContactCreateDTO dto);

    EmergencyContactResponseDTO partialUpdateFromDto(Long id, EmergencyContactUpdateDTO dto);

    EmergencyContactResponseDTO getById(Long id);

    java.util.List<EmergencyContactResponseDTO> search(EmergencyContactFilterDTO filter);

    void delete(Long id);
}
