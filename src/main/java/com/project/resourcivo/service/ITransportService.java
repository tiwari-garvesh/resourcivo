package com.project.resourcivo.service;

import java.util.List;
import com.project.resourcivo.dto.TransportCreateDTO;
import com.project.resourcivo.dto.TransportUpdateDTO;
import com.project.resourcivo.dto.TransportResponseDTO;
import com.project.resourcivo.criteria.TransportFilterDTO;

public interface ITransportService {
    TransportResponseDTO createFromDto(TransportCreateDTO dto);
    TransportResponseDTO updateFromDto(Long id, TransportCreateDTO dto);
    TransportResponseDTO partialUpdateFromDto(Long id, TransportUpdateDTO dto);
    java.util.List<TransportResponseDTO> search(TransportFilterDTO filter);
}
