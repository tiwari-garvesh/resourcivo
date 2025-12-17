package com.project.resourcivo.service;

import com.project.resourcivo.dto.InventoryItemCreateDTO;
import com.project.resourcivo.dto.InventoryItemResponseDTO;
import com.project.resourcivo.exception.ResourceNotFoundException;
import com.project.resourcivo.mapper.InventoryItemMapper;
import com.project.resourcivo.model.InventoryItem;
import com.project.resourcivo.repository.InventoryItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryItemService {

    private final InventoryItemRepository repository;

    public InventoryItemService(InventoryItemRepository repository) {
        this.repository = repository;
    }

    public InventoryItemResponseDTO create(InventoryItemCreateDTO dto) {
        InventoryItem entity = InventoryItemMapper.toEntity(dto);
        return InventoryItemMapper.toResponse(repository.save(entity));
    }

    public InventoryItemResponseDTO getById(Long id) {
        InventoryItem entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory Item", "id", id));
        return InventoryItemMapper.toResponse(entity);
    }

    public List<InventoryItemResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(InventoryItemMapper::toResponse)
                .collect(Collectors.toList());
    }

    public InventoryItemResponseDTO update(Long id, InventoryItemCreateDTO dto) {
        InventoryItem entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory Item", "id", id));

        InventoryItemMapper.updateEntity(dto, entity);
        return InventoryItemMapper.toResponse(repository.save(entity));
    }

    public InventoryItemResponseDTO partialUpdate(Long id, InventoryItemCreateDTO dto) {
        InventoryItem entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory Item", "id", id));

        InventoryItemMapper.updateEntity(dto, entity);
        return InventoryItemMapper.toResponse(repository.save(entity));
    }

    public List<InventoryItemResponseDTO> search(com.project.resourcivo.criteria.InventoryItemFilterDTO filter) {
        return repository.findAll(com.project.resourcivo.specification.InventoryItemSpecification.build(filter))
                .stream()
                .map(InventoryItemMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
