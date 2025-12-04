package com.project.resourcivo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import com.project.resourcivo.repository.InventoryItemRepository;
import com.project.resourcivo.mapper.InventoryItemMapper;
import com.project.resourcivo.model.InventoryItem;
import com.project.resourcivo.dto.InventoryItemCreateDTO;
import com.project.resourcivo.dto.InventoryItemUpdateDTO;
import com.project.resourcivo.dto.InventoryItemResponseDTO;
import com.project.resourcivo.criteria.InventoryItemFilterDTO;
import com.project.resourcivo.specification.InventoryItemSpecification;

@Service
public class InventoryItemService implements IInventoryItemService {

    private final InventoryItemRepository repo;

    public InventoryItemService(InventoryItemRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public InventoryItemResponseDTO createFromDto(InventoryItemCreateDTO dto) {
        InventoryItem e = InventoryItemMapper.toEntity(dto);
        var saved = repo.save(e);
        return InventoryItemMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public InventoryItemResponseDTO updateFromDto(Long id, InventoryItemCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            InventoryItem updated = InventoryItemMapper.toEntity(dto);
            // merge or replace existing fields as needed
            var s = repo.save(existing);
            return InventoryItemMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    public InventoryItemResponseDTO partialUpdateFromDto(Long id, InventoryItemUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            InventoryItemMapper.mergeUpdate(dto, existing);
            var s = repo.save(existing);
            return InventoryItemMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    public List<InventoryItemResponseDTO> search(InventoryItemFilterDTO filter) {
        return repo.findAll(InventoryItemSpecification.build(filter)).stream().map(InventoryItemMapper::toResponse).collect(Collectors.toList());
    }
}
