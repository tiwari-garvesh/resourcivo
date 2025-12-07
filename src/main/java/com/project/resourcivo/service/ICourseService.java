package com.project.resourcivo.service;

import java.util.List;
import com.project.resourcivo.dto.CourseCreateDTO;
import com.project.resourcivo.dto.CourseUpdateDTO;
import com.project.resourcivo.dto.CourseResponseDTO;
import com.project.resourcivo.criteria.CourseFilterDTO;

public interface ICourseService {
    CourseResponseDTO createFromDto(CourseCreateDTO dto);

    CourseResponseDTO updateFromDto(Long id, CourseCreateDTO dto);

    CourseResponseDTO partialUpdateFromDto(Long id, CourseUpdateDTO dto);

    CourseResponseDTO getById(Long id);

    java.util.List<CourseResponseDTO> search(CourseFilterDTO filter);

    void delete(Long id);
}
