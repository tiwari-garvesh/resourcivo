package com.project.resourcivo.specification;

import com.project.resourcivo.criteria.CourseFilterDTO;
import com.project.resourcivo.model.Course;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecification {
    public static Specification<Course> build(CourseFilterDTO f) {
        return (root, query, cb) -> cb.conjunction();
    }
}
