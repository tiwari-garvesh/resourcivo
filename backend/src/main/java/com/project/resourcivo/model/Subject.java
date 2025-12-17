package com.project.resourcivo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "subject", indexes = {
		@Index(name = "idx_subject_name", columnList = "name")
})
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String code;

	// One subject can have many reference books
	@OneToMany
	private List<LibraryBook> refBooks;

	// Many subjects can be taught by many faculty
	@ManyToMany
	private List<Faculty> taughtBy;

	// One subject can be studied by many students
	@OneToMany
	private List<Student> studiedBy;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<LibraryBook> getRefBooks() {
		return refBooks;
	}

	public void setRefBooks(List<LibraryBook> refBooks) {
		this.refBooks = refBooks;
	}

	public List<Faculty> getTaughtBy() {
		return taughtBy;
	}

	public void setTaughtBy(List<Faculty> taughtBy) {
		this.taughtBy = taughtBy;
	}

	public List<Student> getStudiedBy() {
		return studiedBy;
	}

	public void setStudiedBy(List<Student> studiedBy) {
		this.studiedBy = studiedBy;
	}

	public Subject() {
	}

	public Subject(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public Subject(String name, String code, List<LibraryBook> refBooks, List<Faculty> taughtBy,
			List<Student> studiedBy) {
		this.name = name;
		this.code = code;
		this.refBooks = refBooks;
		this.taughtBy = taughtBy;
		this.studiedBy = studiedBy;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", code=" + code + ", refBooks=" + refBooks + ", taughtBy="
				+ taughtBy + ", studiedBy=" + studiedBy + "]";
	}
}