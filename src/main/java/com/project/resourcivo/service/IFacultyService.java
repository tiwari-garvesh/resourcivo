package com.project.resourcivo.service;

import java.util.List;

import com.project.resourcivo.criteria.FacultyFilterDTO;
import com.project.resourcivo.dto.FacultyCreateDTO;
import com.project.resourcivo.dto.FacultyResponseDTO;
import com.project.resourcivo.dto.FacultyUpdateDTO;
import com.project.resourcivo.model.Faculty;

public interface IFacultyService {

    // Existing CRUD (kept)
    Faculty addFaculty(Faculty faculty);
    List<Faculty> getAllFaculty();
    Faculty getFacultyById(Long id);
    Faculty updateFaculty(Long id, Faculty faculty);
    void deleteFaculty(Long id);

    // New DTO-based APIs
    FacultyResponseDTO createFromDto(FacultyCreateDTO dto);
    FacultyResponseDTO updateFromDto(Long id, FacultyCreateDTO dto); // full update via DTO
    FacultyResponseDTO partialUpdateFromDto(Long id, FacultyUpdateDTO dto); // PATCH

    // search
    List<FacultyResponseDTO> search(FacultyFilterDTO filter);
}
