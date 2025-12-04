package com.project.resourcivo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import com.project.resourcivo.repository.EmergencyContactRepository;
import com.project.resourcivo.mapper.EmergencyContactMapper;
import com.project.resourcivo.model.EmergencyContact;
import com.project.resourcivo.dto.EmergencyContactCreateDTO;
import com.project.resourcivo.dto.EmergencyContactUpdateDTO;
import com.project.resourcivo.dto.EmergencyContactResponseDTO;
import com.project.resourcivo.criteria.EmergencyContactFilterDTO;
import com.project.resourcivo.specification.EmergencyContactSpecification;

@Service
public class EmergencyContactService implements IEmergencyContactService {

    private final EmergencyContactRepository repo;

    public EmergencyContactService(EmergencyContactRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public EmergencyContactResponseDTO createFromDto(EmergencyContactCreateDTO dto) {
        EmergencyContact e = EmergencyContactMapper.toEntity(dto);
        var saved = repo.save(e);
        return EmergencyContactMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public EmergencyContactResponseDTO updateFromDto(Long id, EmergencyContactCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            EmergencyContact updated = EmergencyContactMapper.toEntity(dto);
            // merge or replace existing fields as needed
            var s = repo.save(existing);
            return EmergencyContactMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    public EmergencyContactResponseDTO partialUpdateFromDto(Long id, EmergencyContactUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            EmergencyContactMapper.mergeUpdate(dto, existing);
            var s = repo.save(existing);
            return EmergencyContactMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    public List<EmergencyContactResponseDTO> search(EmergencyContactFilterDTO filter) {
        return repo.findAll(EmergencyContactSpecification.build(filter)).stream().map(EmergencyContactMapper::toResponse).collect(Collectors.toList());
    }
}
