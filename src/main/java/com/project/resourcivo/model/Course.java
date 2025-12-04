package com.project.resourcivo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "course", indexes = {
		@Index(name = "idx_course_code", columnList = "code")
})
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String code;

	@ManyToMany
	private List<Subject> subjects;

	@OneToOne
	private Faculty headOfDepartment;

	@OneToMany
	private List<Student> studentsEnrolled;

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

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Faculty getHeadOfDepartment() {
		return headOfDepartment;
	}

	public void setHeadOfDepartment(Faculty headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}

	public List<Student> getStudentsEnrolled() {
		return studentsEnrolled;
	}

	public void setStudentsEnrolled(List<Student> studentsEnrolled) {
		this.studentsEnrolled = studentsEnrolled;
	}

	public Course() {
	}

	public Course(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public Course(Long id, String name, String code, List<Subject> subjects, Faculty headOfDepartment,
			List<Student> studentsEnrolled) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.subjects = subjects;
		this.headOfDepartment = headOfDepartment;
		this.studentsEnrolled = studentsEnrolled;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", code=" + code + ", subjects=" + subjects
				+ ", headOfDepartment=" + headOfDepartment + ", studentsEnrolled=" + studentsEnrolled + "]";
	}
}
