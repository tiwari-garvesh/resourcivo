package com.project.resourcivo.specification;

import com.project.resourcivo.criteria.ClassroomFilterDTO;
import com.project.resourcivo.model.Classroom;
import org.springframework.data.jpa.domain.Specification;

public class ClassroomSpecification {
    public static Specification<Classroom> build(ClassroomFilterDTO f) {
        return (root, query, cb) -> cb.conjunction();
    }
}
