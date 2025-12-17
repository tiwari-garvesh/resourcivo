package com.project.resourcivo.service;

import com.project.resourcivo.dto.TransportManagerCreateDTO;
import com.project.resourcivo.dto.TransportManagerResponseDTO;
import com.project.resourcivo.dto.TransportManagerUpdateDTO;
import com.project.resourcivo.mapper.TransportManagerMapper;
import com.project.resourcivo.repository.TransportManagerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransportManagerService implements ITransportManagerService {

    private final TransportManagerRepository repository;
    private final TransportManagerMapper mapper;

    public TransportManagerService(TransportManagerRepository repository, TransportManagerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TransportManagerResponseDTO createFromDto(TransportManagerCreateDTO dto) {
        var entity = mapper.toEntity(dto);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public TransportManagerResponseDTO updateFromDto(Long id, TransportManagerUpdateDTO dto) {
        var entity = repository.findById(id).orElse(null);
        if (entity == null)
            return null;
        mapper.updateEntityFromDto(entity, dto);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public TransportManagerResponseDTO getById(Long id) {
        return repository.findById(id).map(mapper::toResponse).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransportManagerResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
