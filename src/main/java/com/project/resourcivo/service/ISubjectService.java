package com.project.resourcivo.service;

import java.util.List;
import com.project.resourcivo.dto.SubjectCreateDTO;
import com.project.resourcivo.dto.SubjectUpdateDTO;
import com.project.resourcivo.dto.SubjectResponseDTO;
import com.project.resourcivo.criteria.SubjectFilterDTO;

public interface ISubjectService {
    SubjectResponseDTO createFromDto(SubjectCreateDTO dto);
    SubjectResponseDTO updateFromDto(Long id, SubjectCreateDTO dto);
    SubjectResponseDTO partialUpdateFromDto(Long id, SubjectUpdateDTO dto);
    java.util.List<SubjectResponseDTO> search(SubjectFilterDTO filter);
}
