package com.project.resourcivo.service;

import java.util.List;
import com.project.resourcivo.dto.InventoryItemCreateDTO;
import com.project.resourcivo.dto.InventoryItemUpdateDTO;
import com.project.resourcivo.dto.InventoryItemResponseDTO;
import com.project.resourcivo.criteria.InventoryItemFilterDTO;

public interface IInventoryItemService {
    InventoryItemResponseDTO createFromDto(InventoryItemCreateDTO dto);
    InventoryItemResponseDTO updateFromDto(Long id, InventoryItemCreateDTO dto);
    InventoryItemResponseDTO partialUpdateFromDto(Long id, InventoryItemUpdateDTO dto);
    java.util.List<InventoryItemResponseDTO> search(InventoryItemFilterDTO filter);
}
