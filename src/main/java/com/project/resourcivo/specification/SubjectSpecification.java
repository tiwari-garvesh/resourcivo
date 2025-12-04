package com.project.resourcivo.specification;

import com.project.resourcivo.criteria.SubjectFilterDTO;
import com.project.resourcivo.model.Subject;
import org.springframework.data.jpa.domain.Specification;

public class SubjectSpecification {
    public static Specification<Subject> build(SubjectFilterDTO f) {
        return (root, query, cb) -> cb.conjunction();
    }
}
