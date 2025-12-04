package com.project.resourcivo.specification;

import com.project.resourcivo.criteria.ContactFilterDTO;
import com.project.resourcivo.model.Contact;
import org.springframework.data.jpa.domain.Specification;

public class ContactSpecification {
    public static Specification<Contact> build(ContactFilterDTO f) {
        return (root, query, cb) -> cb.conjunction();
    }
}
