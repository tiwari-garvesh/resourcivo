package com.project.resourcivo.mapper;

import com.project.resourcivo.dto.InventoryItemCreateDTO;
import com.project.resourcivo.dto.InventoryItemResponseDTO;
import com.project.resourcivo.model.InventoryItem;

public class InventoryItemMapper {

    public static InventoryItem toEntity(InventoryItemCreateDTO dto) {
        if (dto == null)
            return null;
        InventoryItem e = new InventoryItem();
        updateEntity(dto, e);
        return e;
    }

    public static void updateEntity(InventoryItemCreateDTO dto, InventoryItem e) {
        if (dto == null || e == null)
            return;
        if (dto.getName() != null)
            e.setName(dto.getName());
        if (dto.getSku() != null)
            e.setSku(dto.getSku());
        if (dto.getCategory() != null)
            e.setCategory(dto.getCategory());
        if (dto.getQuantity() != null)
            e.setQuantity(dto.getQuantity());
        if (dto.getUnitOfMeasure() != null)
            e.setUnitOfMeasure(dto.getUnitOfMeasure());
        if (dto.getReorderLevel() != null)
            e.setReorderLevel(dto.getReorderLevel());
        if (dto.getSupplier() != null)
            e.setSupplier(dto.getSupplier());
        if (dto.getExpiryDate() != null)
            e.setExpiryDate(dto.getExpiryDate());
        if (dto.getPricePerUnit() != null)
            e.setPricePerUnit(dto.getPricePerUnit());
    }

    public static InventoryItemResponseDTO toResponse(InventoryItem e) {
        if (e == null)
            return null;
        InventoryItemResponseDTO r = new InventoryItemResponseDTO();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setSku(e.getSku());
        r.setCategory(e.getCategory());
        r.setQuantity(e.getQuantity());
        r.setUnitOfMeasure(e.getUnitOfMeasure());
        r.setReorderLevel(e.getReorderLevel());
        r.setSupplier(e.getSupplier());
        r.setExpiryDate(e.getExpiryDate());
        r.setPricePerUnit(e.getPricePerUnit());
        return r;
    }
}
