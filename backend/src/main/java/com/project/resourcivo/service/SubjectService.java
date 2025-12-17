package com.project.resourcivo.service;

import org.springframework.cache.annotation.CacheEvict;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import com.project.resourcivo.repository.SubjectRepository;
import com.project.resourcivo.mapper.SubjectMapper;
import com.project.resourcivo.model.Subject;
import com.project.resourcivo.dto.SubjectCreateDTO;
import com.project.resourcivo.dto.SubjectUpdateDTO;
import com.project.resourcivo.dto.SubjectResponseDTO;
import com.project.resourcivo.criteria.SubjectFilterDTO;
import com.project.resourcivo.specification.SubjectSpecification;

@Service
public class SubjectService implements ISubjectService {

    private final SubjectRepository repo;

    public SubjectService(SubjectRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public SubjectResponseDTO createFromDto(SubjectCreateDTO dto) {
        Subject e = SubjectMapper.toEntity(dto);
        var saved = repo.save(e);
        return SubjectMapper.toResponse(saved);
    }

    @Override
    @Transactional
    @CacheEvict(value = "subjects", allEntries = true)
    public SubjectResponseDTO updateFromDto(Long id, SubjectCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            SubjectMapper.updateEntity(dto, existing);
            var s = repo.save(existing);
            return SubjectMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    @CacheEvict(value = "subjects", allEntries = true)
    public SubjectResponseDTO partialUpdateFromDto(Long id, SubjectUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            SubjectMapper.mergeUpdate(dto, existing);
            var s = repo.save(existing);
            return SubjectMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    public SubjectResponseDTO getById(Long id) {
        return repo.findById(id).map(SubjectMapper::toResponse).orElse(null);
    }

    @Override
    public List<SubjectResponseDTO> search(SubjectFilterDTO filter) {
        return repo.findAll(SubjectSpecification.build(filter)).stream().map(SubjectMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @CacheEvict(value = "subjects", allEntries = true)
    public void delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
    }

    @Override
    public List<SubjectResponseDTO> getAll() {
        return repo.findAll().stream().map(SubjectMapper::toResponse).collect(Collectors.toList());
    }
}
