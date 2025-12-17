package com.project.resourcivo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student", indexes = {
		@Index(name = "idx_student_course", columnList = "course"),
		@Index(name = "idx_student_date_joining", columnList = "dateOfJoining")
})
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long id;

	private String firstName;

	private String middleName;

	private String lastName;

	private LocalDate dateOfBirth;

	@OneToOne(cascade = jakarta.persistence.CascadeType.ALL)
	private Address currentAddress;

	@OneToOne(cascade = jakarta.persistence.CascadeType.ALL)
	private Address permanentAddress;

	@OneToOne(cascade = jakarta.persistence.CascadeType.ALL)
	private Contact contact;

	@jakarta.persistence.OneToMany(cascade = jakarta.persistence.CascadeType.ALL)
	private java.util.List<EmergencyContact> emergencyContacts;

	private LocalDate dateOfJoining;

	private LocalDate yearOfGraduation;

	private String course;

	private String bio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(Address currentAddress) {
		this.currentAddress = currentAddress;
	}

	public Address getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public java.util.List<EmergencyContact> getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(java.util.List<EmergencyContact> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public LocalDate getYearOfGraduation() {
		return yearOfGraduation;
	}

	public void setYearOfGraduation(LocalDate yearOfGraduation) {
		this.yearOfGraduation = yearOfGraduation;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Student() {
		super();
	}

	public Student(String firstName, String lastName, LocalDate dateOfBirth, Address currentAddress,
			Address permanentAddress, Contact contact, String course) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.currentAddress = currentAddress;
		this.permanentAddress = permanentAddress;
		this.contact = contact;
		this.dateOfJoining = LocalDate.now();
		this.course = course;
	}

	public Student(String firstName, String middleName, String lastName, LocalDate dateOfBirth, Address currentAddress,
			Address permanentAddress, Contact contact, LocalDate yearOfGraduation, String course, String bio) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.currentAddress = currentAddress;
		this.permanentAddress = permanentAddress;
		this.contact = contact;
		this.dateOfJoining = LocalDate.now();
		this.yearOfGraduation = yearOfGraduation;
		this.course = course;
		this.bio = bio;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", currentAddress=" + currentAddress
				+ ", permanentAddress=" + permanentAddress + ", contact=" + contact + ", dateOfJoining=" + dateOfJoining
				+ ", yearOfGraduation=" + yearOfGraduation + ", course=" + course + ", bio=" + bio + "]";
	}

}
