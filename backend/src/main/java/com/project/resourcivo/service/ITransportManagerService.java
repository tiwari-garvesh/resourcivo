package com.project.resourcivo.service;

import com.project.resourcivo.dto.TransportManagerCreateDTO;
import com.project.resourcivo.dto.TransportManagerResponseDTO;
import com.project.resourcivo.dto.TransportManagerUpdateDTO;
import java.util.List;

public interface ITransportManagerService {
    TransportManagerResponseDTO createFromDto(TransportManagerCreateDTO dto);

    TransportManagerResponseDTO updateFromDto(Long id, TransportManagerUpdateDTO dto);

    TransportManagerResponseDTO getById(Long id);

    List<TransportManagerResponseDTO> getAll();

    void delete(Long id);
}
