package com.project.resourcivo.service;

import com.project.resourcivo.dto.LibraryCreateDTO;
import com.project.resourcivo.dto.LibraryResponseDTO;
import com.project.resourcivo.dto.LibraryUpdateDTO;
import java.util.List;

public interface ILibraryService {
    LibraryResponseDTO createFromDto(LibraryCreateDTO dto);

    LibraryResponseDTO updateFromDto(Long id, LibraryUpdateDTO dto);

    LibraryResponseDTO getById(Long id);

    List<LibraryResponseDTO> getAll();
}
