package com.project.resourcivo.service;

import com.project.resourcivo.dto.LibrarianCreateDTO;
import com.project.resourcivo.dto.LibrarianResponseDTO;
import com.project.resourcivo.dto.LibrarianUpdateDTO;
import java.util.List;

public interface ILibrarianService {
    LibrarianResponseDTO createFromDto(LibrarianCreateDTO dto);

    LibrarianResponseDTO updateFromDto(Long id, LibrarianUpdateDTO dto);

    LibrarianResponseDTO getById(Long id);

    List<LibrarianResponseDTO> getAll();

    void delete(Long id);
}
