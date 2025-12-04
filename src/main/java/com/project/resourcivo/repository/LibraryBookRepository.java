package com.project.resourcivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.resourcivo.model.LibraryBook;

@Repository
public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long>, JpaSpecificationExecutor<LibraryBook> {

}
