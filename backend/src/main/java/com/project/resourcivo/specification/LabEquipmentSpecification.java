package com.project.resourcivo.specification;

import com.project.resourcivo.criteria.LabEquipmentFilterDTO;
import com.project.resourcivo.model.LabEquipment;
import org.springframework.data.jpa.domain.Specification;

public class LabEquipmentSpecification {
    public static Specification<LabEquipment> build(LabEquipmentFilterDTO f) {
        return (root, query, cb) -> cb.conjunction();
    }
}
