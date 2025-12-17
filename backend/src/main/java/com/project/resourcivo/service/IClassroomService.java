package com.project.resourcivo.service;

import java.util.List;
import com.project.resourcivo.dto.ClassroomCreateDTO;
import com.project.resourcivo.dto.ClassroomUpdateDTO;
import com.project.resourcivo.dto.ClassroomResponseDTO;
import com.project.resourcivo.criteria.ClassroomFilterDTO;

public interface IClassroomService {
    ClassroomResponseDTO createFromDto(ClassroomCreateDTO dto);

    ClassroomResponseDTO updateFromDto(Long id, ClassroomCreateDTO dto);

    ClassroomResponseDTO partialUpdateFromDto(Long id, ClassroomUpdateDTO dto);

    ClassroomResponseDTO getById(Long id);

    java.util.List<ClassroomResponseDTO> search(ClassroomFilterDTO filter);

    List<ClassroomResponseDTO> getAll();

    void delete(Long id);
}
