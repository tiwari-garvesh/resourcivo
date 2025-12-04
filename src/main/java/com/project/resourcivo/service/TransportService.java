package com.project.resourcivo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import com.project.resourcivo.repository.TransportRepository;
import com.project.resourcivo.mapper.TransportMapper;
import com.project.resourcivo.model.Transport;
import com.project.resourcivo.dto.TransportCreateDTO;
import com.project.resourcivo.dto.TransportUpdateDTO;
import com.project.resourcivo.dto.TransportResponseDTO;
import com.project.resourcivo.criteria.TransportFilterDTO;
import com.project.resourcivo.specification.TransportSpecification;

@Service
public class TransportService implements ITransportService {

    private final TransportRepository repo;

    public TransportService(TransportRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public TransportResponseDTO createFromDto(TransportCreateDTO dto) {
        Transport e = TransportMapper.toEntity(dto);
        var saved = repo.save(e);
        return TransportMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public TransportResponseDTO updateFromDto(Long id, TransportCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            Transport updated = TransportMapper.toEntity(dto);
            // merge or replace existing fields as needed
            var s = repo.save(existing);
            return TransportMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    public TransportResponseDTO partialUpdateFromDto(Long id, TransportUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            TransportMapper.mergeUpdate(dto, existing);
            var s = repo.save(existing);
            return TransportMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    public List<TransportResponseDTO> search(TransportFilterDTO filter) {
        return repo.findAll(TransportSpecification.build(filter)).stream().map(TransportMapper::toResponse).collect(Collectors.toList());
    }
}
