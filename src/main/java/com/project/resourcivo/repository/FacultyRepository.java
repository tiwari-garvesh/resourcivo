package com.project.resourcivo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.project.resourcivo.model.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long>, JpaSpecificationExecutor<Faculty> {

	List<Faculty> findByFirstName(String firstName);

	List<Faculty> findByMiddleName(String middleName);

	List<Faculty> findByLastName(String lastName);

	List<Faculty> findByDateOfBirth(LocalDate dob);

	List<Faculty> findByLevel(String level);

	List<Faculty> findBySubject(String subject);

	List<Faculty> findByYearOfExperience(Integer years);
}
