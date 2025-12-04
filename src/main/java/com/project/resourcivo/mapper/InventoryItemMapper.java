package com.project.resourcivo.mapper;

import com.project.resourcivo.model.InventoryItem;
import com.project.resourcivo.dto.InventoryItemCreateDTO;
import com.project.resourcivo.dto.InventoryItemUpdateDTO;
import com.project.resourcivo.dto.InventoryItemResponseDTO;

public final class InventoryItemMapper {

    private InventoryItemMapper() {}

    public static InventoryItem toEntity(InventoryItemCreateDTO dto) {
        if (dto == null) return null;
        InventoryItem e = new InventoryItem();

        return e;
    }

    public static void mergeUpdate(InventoryItemUpdateDTO dto, InventoryItem entity) {
        if (dto == null || entity == null) return;

    }

    public static InventoryItemResponseDTO toResponse(InventoryItem e) {
        if (e == null) return null;
        InventoryItemResponseDTO r = new InventoryItemResponseDTO();

        return r;
    }
}
