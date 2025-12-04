package com.project.resourcivo.service;

import java.util.List;
import com.project.resourcivo.dto.StudentCreateDTO;
import com.project.resourcivo.dto.StudentUpdateDTO;
import com.project.resourcivo.dto.StudentResponseDTO;
import com.project.resourcivo.criteria.StudentFilterDTO;

public interface IStudentService {
    StudentResponseDTO createFromDto(StudentCreateDTO dto);
    StudentResponseDTO updateFromDto(Long id, StudentCreateDTO dto);
    StudentResponseDTO partialUpdateFromDto(Long id, StudentUpdateDTO dto);
    java.util.List<StudentResponseDTO> search(StudentFilterDTO filter);
}
