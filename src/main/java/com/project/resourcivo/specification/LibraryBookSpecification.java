package com.project.resourcivo.specification;

import com.project.resourcivo.criteria.LibraryBookFilterDTO;
import com.project.resourcivo.model.LibraryBook;
import org.springframework.data.jpa.domain.Specification;

public class LibraryBookSpecification {
    public static Specification<LibraryBook> build(LibraryBookFilterDTO f) {
        return (root, query, cb) -> cb.conjunction();
    }
}
