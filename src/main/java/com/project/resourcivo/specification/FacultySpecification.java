package com.project.resourcivo.specification;

import com.project.resourcivo.criteria.FacultyFilterDTO;
import com.project.resourcivo.model.Faculty;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class FacultySpecification {

    public static Specification<Faculty> build(FacultyFilterDTO c) {
        return (root, query, cb) -> {
            var predicate = cb.conjunction();

            if (StringUtils.hasText(c.getFirstName())) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("firstName")), "%" + c.getFirstName().toLowerCase() + "%"));
            }

            if (StringUtils.hasText(c.getLastName())) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("lastName")), "%" + c.getLastName().toLowerCase() + "%"));
            }

            if (StringUtils.hasText(c.getLevel())) {
                predicate = cb.and(predicate, cb.equal(root.get("level"), c.getLevel()));
            }

            if (StringUtils.hasText(c.getSubject())) {
                Join<Object, Object> subjectJoin = root.join("subject", JoinType.LEFT);
                Path<String> subjectNamePath = subjectJoin.get("name");
                predicate = cb.and(predicate, cb.equal(subjectNamePath, c.getSubject()));
            }

            if (c.getDob() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("dateOfBirth"), c.getDob()));
            }

            if (c.getMinExperience() != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("yearOfExperience"), c.getMinExperience()));
            }

            if (c.getMaxExperience() != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("yearOfExperience"), c.getMaxExperience()));
            }

            return predicate;
        };
    }
}
