package com.project.resourcivo.service;

import java.util.List;
import com.project.resourcivo.dto.AddressCreateDTO;
import com.project.resourcivo.dto.AddressUpdateDTO;
import com.project.resourcivo.dto.AddressResponseDTO;
import com.project.resourcivo.criteria.AddressFilterDTO;

public interface IAddressService {
    AddressResponseDTO createFromDto(AddressCreateDTO dto);

    AddressResponseDTO updateFromDto(Long id, AddressCreateDTO dto);

    AddressResponseDTO partialUpdateFromDto(Long id, AddressUpdateDTO dto);

    AddressResponseDTO getById(Long id);

    java.util.List<AddressResponseDTO> search(AddressFilterDTO filter);
}
