package com.project.resourcivo.specification;

import com.project.resourcivo.criteria.EmergencyContactFilterDTO;
import com.project.resourcivo.model.EmergencyContact;
import org.springframework.data.jpa.domain.Specification;

public class EmergencyContactSpecification {
    public static Specification<EmergencyContact> build(EmergencyContactFilterDTO f) {
        return (root, query, cb) -> cb.conjunction();
    }
}
