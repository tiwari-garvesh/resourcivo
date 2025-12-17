package com.project.resourcivo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.resourcivo.criteria.FacultyFilterDTO;
import com.project.resourcivo.dto.FacultyCreateDTO;
import com.project.resourcivo.dto.FacultyResponseDTO;
import com.project.resourcivo.dto.FacultyUpdateDTO;
import com.project.resourcivo.mapper.FacultyMapper;
import com.project.resourcivo.model.Faculty;
import com.project.resourcivo.repository.FacultyRepository;
import com.project.resourcivo.specification.FacultySpecification;

@Service
public class FacultyService implements IFacultyService {

    private final FacultyRepository facultyRepository;
    private final com.project.resourcivo.repository.SubjectRepository subjectRepository;

    public FacultyService(FacultyRepository facultyRepository,
            com.project.resourcivo.repository.SubjectRepository subjectRepository) {
        this.facultyRepository = facultyRepository;
        this.subjectRepository = subjectRepository;
    }

    // ---------------- Full CRUD (existing)
    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    @Cacheable(value = "faculty", key = "'all'")
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    @Override
    @Cacheable(value = "faculty", key = "#id")
    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    @CacheEvict(value = "faculty", allEntries = true)
    public Faculty updateFaculty(Long id, Faculty updated) {
        return facultyRepository.findById(id).map(existing -> {
            existing.setFirstName(updated.getFirstName());
            existing.setMiddleName(updated.getMiddleName());
            existing.setLastName(updated.getLastName());
            existing.setDateOfBirth(updated.getDateOfBirth());
            existing.setCurrentAddress(updated.getCurrentAddress());
            existing.setPermanentAddress(updated.getPermanentAddress());
            existing.setContact(updated.getContact());
            existing.setDateOfJoining(updated.getDateOfJoining());
            existing.setLevel(updated.getLevel());
            existing.setSubject(updated.getSubject());
            existing.setYearOfExperience(updated.getYearOfExperience());
            existing.setBio(updated.getBio());
            return facultyRepository.save(existing);
        }).orElse(null);
    }

    @Override
    @Transactional
    @CacheEvict(value = "faculty", allEntries = true)
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    @Transactional
    public FacultyResponseDTO createFromDto(FacultyCreateDTO dto) {
        Faculty f = FacultyMapper.toEntity(dto);
        Faculty saved = facultyRepository.save(f);
        return FacultyMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public FacultyResponseDTO updateFromDto(Long id, FacultyCreateDTO dto) {
        return facultyRepository.findById(id).map(existing -> {
            // map all fields from create-dto (full replace for fields provided)
            Faculty updated = FacultyMapper.toEntity(dto);
            // keep id and any fields that must not be overwritten from existing if dto had
            // nulls
            if (updated.getFirstName() != null)
                existing.setFirstName(updated.getFirstName());
            existing.setMiddleName(updated.getMiddleName());
            if (updated.getLastName() != null)
                existing.setLastName(updated.getLastName());
            if (updated.getDateOfBirth() != null)
                existing.setDateOfBirth(updated.getDateOfBirth());
            if (updated.getCurrentAddress() != null)
                existing.setCurrentAddress(updated.getCurrentAddress());
            if (updated.getPermanentAddress() != null)
                existing.setPermanentAddress(updated.getPermanentAddress());
            if (updated.getContact() != null)
                existing.setContact(updated.getContact());
            if (updated.getDateOfJoining() != null)
                existing.setDateOfJoining(updated.getDateOfJoining());
            if (updated.getLevel() != null)
                existing.setLevel(updated.getLevel());
            if (updated.getSubject() != null)
                existing.setSubject(updated.getSubject());
            if (updated.getYearOfExperience() != null)
                existing.setYearOfExperience(updated.getYearOfExperience());
            if (updated.getBio() != null)
                existing.setBio(updated.getBio());
            Faculty s = facultyRepository.save(existing);
            return FacultyMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    public FacultyResponseDTO partialUpdateFromDto(Long id, FacultyUpdateDTO dto) {
        return facultyRepository.findById(id).map(existing -> {
            FacultyMapper.mergeUpdate(dto, existing);
            Faculty saved = facultyRepository.save(existing);
            return FacultyMapper.toResponse(saved);
        }).orElse(null);
    }

    // ---------------- Specification search
    @Override
    public List<FacultyResponseDTO> search(FacultyFilterDTO filter) {
        return facultyRepository.findAll(FacultySpecification.build(filter))
                .stream().map(FacultyMapper::toResponse).collect(Collectors.toList());
    }

    private com.project.resourcivo.model.Subject resolveSubjectFromRef(com.project.resourcivo.dto.SubjectRefDTO sref) {
        if (sref == null)
            return null;
        if (sref.getId() != null) {
            return subjectRepository.findById(sref.getId())
                    .orElseThrow(() -> new com.project.resourcivo.exception.ResourceNotFoundException("Subject", "id",
                            sref.getId()));
        }
        // fallback: try find by name (not implemented - returning new Subject stub)
        com.project.resourcivo.model.Subject s = new com.project.resourcivo.model.Subject();
        s.setName(sref.getName());
        return s;
    }

}