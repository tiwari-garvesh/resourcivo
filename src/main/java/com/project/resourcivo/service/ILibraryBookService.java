package com.project.resourcivo.service;

import java.util.List;
import com.project.resourcivo.dto.LibraryBookCreateDTO;
import com.project.resourcivo.dto.LibraryBookUpdateDTO;
import com.project.resourcivo.dto.LibraryBookResponseDTO;
import com.project.resourcivo.criteria.LibraryBookFilterDTO;

public interface ILibraryBookService {
    LibraryBookResponseDTO createFromDto(LibraryBookCreateDTO dto);
    LibraryBookResponseDTO updateFromDto(Long id, LibraryBookCreateDTO dto);
    LibraryBookResponseDTO partialUpdateFromDto(Long id, LibraryBookUpdateDTO dto);
    java.util.List<LibraryBookResponseDTO> search(LibraryBookFilterDTO filter);
}
