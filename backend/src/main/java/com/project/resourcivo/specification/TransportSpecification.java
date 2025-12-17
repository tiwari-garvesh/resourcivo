package com.project.resourcivo.specification;

import com.project.resourcivo.criteria.TransportFilterDTO;
import com.project.resourcivo.model.Transport;
import org.springframework.data.jpa.domain.Specification;

public class TransportSpecification {
    public static Specification<Transport> build(TransportFilterDTO f) {
        return (root, query, cb) -> cb.conjunction();
    }
}
