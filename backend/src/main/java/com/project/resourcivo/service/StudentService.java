package com.project.resourcivo.service;

import org.springframework.cache.annotation.CacheEvict;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import com.project.resourcivo.repository.StudentRepository;
import com.project.resourcivo.mapper.StudentMapper;
import com.project.resourcivo.model.Student;
import com.project.resourcivo.dto.StudentCreateDTO;
import com.project.resourcivo.dto.StudentUpdateDTO;
import com.project.resourcivo.dto.StudentResponseDTO;
import com.project.resourcivo.criteria.StudentFilterDTO;
import com.project.resourcivo.specification.StudentSpecification;
import com.project.resourcivo.exception.ResourceNotFoundException;

@Service
public class StudentService implements IStudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public StudentResponseDTO createFromDto(StudentCreateDTO dto) {
        Student e = StudentMapper.toEntity(dto);
        var saved = repo.save(e);
        return StudentMapper.toResponse(saved);
    }

    @Override
    @Transactional
    @CacheEvict(value = "students", allEntries = true)
    public StudentResponseDTO updateFromDto(Long id, StudentCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            StudentMapper.updateEntity(dto, existing);
            var s = repo.save(existing);
            return StudentMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    @CacheEvict(value = "students", allEntries = true)
    public StudentResponseDTO partialUpdateFromDto(Long id, StudentUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            StudentMapper.mergeUpdate(dto, existing);
            var s = repo.save(existing);
            return StudentMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    public List<StudentResponseDTO> search(StudentFilterDTO filter) {
        return repo.findAll(StudentSpecification.build(filter)).stream().map(StudentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentResponseDTO addEmergencyContact(Long studentId,
            com.project.resourcivo.dto.EmergencyContactCreateDTO dto) {
        Student student = repo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        if (student.getEmergencyContacts() != null && student.getEmergencyContacts().size() >= 2) {
            throw new IllegalStateException("Maximum of 2 emergency contacts allowed");
        }

        com.project.resourcivo.model.EmergencyContact contact = com.project.resourcivo.mapper.EmergencyContactMapper
                .toEntity(dto);

        if (student.getEmergencyContacts() == null) {
            student.setEmergencyContacts(new java.util.ArrayList<>());
        }

        student.getEmergencyContacts().add(contact);
        return StudentMapper.toResponse(repo.save(student));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
    }

    @Override
    public List<StudentResponseDTO> getAll() {
        return repo.findAll().stream().map(StudentMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> findByCourse(String course) {
        return repo.findByCourse(course).stream().map(StudentMapper::toResponse).collect(Collectors.toList());
    }
}
