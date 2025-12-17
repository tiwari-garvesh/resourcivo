package com.project.resourcivo.service;

import com.project.resourcivo.dto.LabEquipmentCreateDTO;
import com.project.resourcivo.dto.LabEquipmentResponseDTO;
import com.project.resourcivo.exception.ResourceNotFoundException;
import com.project.resourcivo.mapper.LabEquipmentMapper;
import com.project.resourcivo.model.LabEquipment;
import com.project.resourcivo.repository.LabEquipmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LabEquipmentService {

    private final LabEquipmentRepository repository;

    public LabEquipmentService(LabEquipmentRepository repository) {
        this.repository = repository;
    }

    public LabEquipmentResponseDTO create(LabEquipmentCreateDTO dto) {
        LabEquipment entity = LabEquipmentMapper.toEntity(dto);
        return LabEquipmentMapper.toResponse(repository.save(entity));
    }

    public LabEquipmentResponseDTO getById(Long id) {
        LabEquipment entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lab Equipment", "id", id));
        return LabEquipmentMapper.toResponse(entity);
    }

    public List<LabEquipmentResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(LabEquipmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public LabEquipmentResponseDTO update(Long id, LabEquipmentCreateDTO dto) {
        LabEquipment entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lab Equipment", "id", id));

        LabEquipmentMapper.updateEntity(dto, entity);
        return LabEquipmentMapper.toResponse(repository.save(entity));
    }

    public LabEquipmentResponseDTO partialUpdate(Long id, LabEquipmentCreateDTO dto) {
        LabEquipment entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lab Equipment", "id", id));

        LabEquipmentMapper.updateEntity(dto, entity);
        return LabEquipmentMapper.toResponse(repository.save(entity));
    }

    public List<LabEquipmentResponseDTO> search(com.project.resourcivo.criteria.LabEquipmentFilterDTO filter) {
        return repository.findAll(com.project.resourcivo.specification.LabEquipmentSpecification.build(filter)).stream()
                .map(LabEquipmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
