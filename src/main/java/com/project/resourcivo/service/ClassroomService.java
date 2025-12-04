package com.project.resourcivo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import com.project.resourcivo.repository.ClassroomRepository;
import com.project.resourcivo.mapper.ClassroomMapper;
import com.project.resourcivo.model.Classroom;
import com.project.resourcivo.dto.ClassroomCreateDTO;
import com.project.resourcivo.dto.ClassroomUpdateDTO;
import com.project.resourcivo.dto.ClassroomResponseDTO;
import com.project.resourcivo.criteria.ClassroomFilterDTO;
import com.project.resourcivo.specification.ClassroomSpecification;

@Service
public class ClassroomService implements IClassroomService {

    private final ClassroomRepository repo;

    public ClassroomService(ClassroomRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public ClassroomResponseDTO createFromDto(ClassroomCreateDTO dto) {
        Classroom e = ClassroomMapper.toEntity(dto);
        var saved = repo.save(e);
        return ClassroomMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ClassroomResponseDTO updateFromDto(Long id, ClassroomCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            Classroom updated = ClassroomMapper.toEntity(dto);
            // merge or replace existing fields as needed
            var s = repo.save(existing);
            return ClassroomMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    public ClassroomResponseDTO partialUpdateFromDto(Long id, ClassroomUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            ClassroomMapper.mergeUpdate(dto, existing);
            var s = repo.save(existing);
            return ClassroomMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    public List<ClassroomResponseDTO> search(ClassroomFilterDTO filter) {
        return repo.findAll(ClassroomSpecification.build(filter)).stream().map(ClassroomMapper::toResponse).collect(Collectors.toList());
    }
}
