package com.project.resourcivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.resourcivo.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
	// Filters by fields
	List<Student> findByFirstName(String firstName);

	List<Student> findByMiddleName(String middleName);

	List<Student> findByLastName(String lastName);

	List<Student> findByDateOfBirth(LocalDate dateOfBirth);

	List<Student> findByCourse(String course);

	List<Student> findByYearOfGraduation(LocalDate yearOfGraduation);
}
