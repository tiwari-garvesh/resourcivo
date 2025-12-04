package com.project.resourcivo.service;

import java.util.List;
import com.project.resourcivo.dto.ContactCreateDTO;
import com.project.resourcivo.dto.ContactUpdateDTO;
import com.project.resourcivo.dto.ContactResponseDTO;
import com.project.resourcivo.criteria.ContactFilterDTO;

public interface IContactService {
    ContactResponseDTO createFromDto(ContactCreateDTO dto);
    ContactResponseDTO updateFromDto(Long id, ContactCreateDTO dto);
    ContactResponseDTO partialUpdateFromDto(Long id, ContactUpdateDTO dto);
    java.util.List<ContactResponseDTO> search(ContactFilterDTO filter);
}
