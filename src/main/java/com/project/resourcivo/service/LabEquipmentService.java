package com.project.resourcivo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import com.project.resourcivo.repository.LabEquipmentRepository;
import com.project.resourcivo.mapper.LabEquipmentMapper;
import com.project.resourcivo.model.LabEquipment;
import com.project.resourcivo.dto.LabEquipmentCreateDTO;
import com.project.resourcivo.dto.LabEquipmentUpdateDTO;
import com.project.resourcivo.dto.LabEquipmentResponseDTO;
import com.project.resourcivo.criteria.LabEquipmentFilterDTO;
import com.project.resourcivo.specification.LabEquipmentSpecification;

@Service
public class LabEquipmentService implements ILabEquipmentService {

    private final LabEquipmentRepository repo;

    public LabEquipmentService(LabEquipmentRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public LabEquipmentResponseDTO createFromDto(LabEquipmentCreateDTO dto) {
        LabEquipment e = LabEquipmentMapper.toEntity(dto);
        var saved = repo.save(e);
        return LabEquipmentMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public LabEquipmentResponseDTO updateFromDto(Long id, LabEquipmentCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            LabEquipment updated = LabEquipmentMapper.toEntity(dto);
            // merge or replace existing fields as needed
            var s = repo.save(existing);
            return LabEquipmentMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    public LabEquipmentResponseDTO partialUpdateFromDto(Long id, LabEquipmentUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            LabEquipmentMapper.mergeUpdate(dto, existing);
            var s = repo.save(existing);
            return LabEquipmentMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    public List<LabEquipmentResponseDTO> search(LabEquipmentFilterDTO filter) {
        return repo.findAll(LabEquipmentSpecification.build(filter)).stream().map(LabEquipmentMapper::toResponse).collect(Collectors.toList());
    }
}
