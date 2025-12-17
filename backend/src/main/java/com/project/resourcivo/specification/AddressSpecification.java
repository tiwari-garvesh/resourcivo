package com.project.resourcivo.specification;

import com.project.resourcivo.criteria.AddressFilterDTO;
import com.project.resourcivo.model.Address;
import org.springframework.data.jpa.domain.Specification;

public class AddressSpecification {
    public static Specification<Address> build(AddressFilterDTO f) {
        return (root, query, cb) -> cb.conjunction();
    }
}
