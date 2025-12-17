package com.project.resourcivo.service;

import org.springframework.cache.annotation.CacheEvict;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import com.project.resourcivo.repository.CourseRepository;
import com.project.resourcivo.mapper.CourseMapper;
import com.project.resourcivo.model.Course;
import com.project.resourcivo.dto.CourseCreateDTO;
import com.project.resourcivo.dto.CourseUpdateDTO;
import com.project.resourcivo.dto.CourseResponseDTO;
import com.project.resourcivo.criteria.CourseFilterDTO;
import com.project.resourcivo.specification.CourseSpecification;

@Service
public class CourseService implements ICourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public CourseResponseDTO createFromDto(CourseCreateDTO dto) {
        Course e = CourseMapper.toEntity(dto);
        var saved = repo.save(e);
        return CourseMapper.toResponse(saved);
    }

    @Override
    @Transactional
    @CacheEvict(value = "courses", allEntries = true)
    public CourseResponseDTO updateFromDto(Long id, CourseCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            CourseMapper.updateEntity(dto, existing);
            var s = repo.save(existing);
            return CourseMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    @CacheEvict(value = "courses", allEntries = true)
    public CourseResponseDTO partialUpdateFromDto(Long id, CourseUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            CourseMapper.mergeUpdate(dto, existing);
            var s = repo.save(existing);
            return CourseMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    public CourseResponseDTO getById(Long id) {
        return repo.findById(id).map(CourseMapper::toResponse).orElse(null);
    }

    @Override
    public List<CourseResponseDTO> search(CourseFilterDTO filter) {
        return repo.findAll(CourseSpecification.build(filter)).stream().map(CourseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @CacheEvict(value = "courses", allEntries = true)
    public void delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
    }
}
