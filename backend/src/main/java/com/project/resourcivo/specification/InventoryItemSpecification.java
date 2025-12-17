package com.project.resourcivo.specification;

import com.project.resourcivo.criteria.InventoryItemFilterDTO;
import com.project.resourcivo.model.InventoryItem;
import org.springframework.data.jpa.domain.Specification;

public class InventoryItemSpecification {
    public static Specification<InventoryItem> build(InventoryItemFilterDTO f) {
        return (root, query, cb) -> cb.conjunction();
    }
}
