package com.project.resourcivo.specification;

import com.project.resourcivo.criteria.StudentFilterDTO;
import com.project.resourcivo.model.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {
    public static Specification<Student> build(StudentFilterDTO f) {
        return (root, query, cb) -> cb.conjunction();
    }
}
