package com.project.resourcivo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faculty_id")
	private Long id;

	private String firstName;

	private String middleName;

	private String lastName;

	private LocalDate dateOfBirth;

	@OneToOne
	private Address currentAddress;

	@OneToOne
	private Address permanentAddress;

	@OneToOne
	private Contact contact;

	private LocalDate dateOfJoining;

	private String level;

	private String subject;

	private Integer yearOfExperience;

	private String bio;

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

	public Address getCuttentAddress() {
		return currentAddress;
	}

	public void setCuttentAddress(Address currentAddress) {
		this.currentAddress = currentAddress;
	}

	public Address getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getYearOfExperience() {
		return yearOfExperience;
	}

	public void setYearOfExperience(Integer yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Faculty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Faculty(String firstName, String lastName, LocalDate dateOfBirth, Address currentAddress,
			Address permanentAddress, Contact contact) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.currentAddress = currentAddress;
		this.permanentAddress = permanentAddress;
		this.contact = contact;
		this.dateOfJoining = LocalDate.now();
	}

	public Faculty(String firstName, String middleName, String lastName, LocalDate dateOfBirth, Address currentAddress,
			Address permanentAddress, Contact contact, String level, String subject,
			Integer yearOfExperience, String bio) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.currentAddress = currentAddress;
		this.permanentAddress = permanentAddress;
		this.contact = contact;
		this.dateOfJoining = LocalDate.now();
		this.level = level;
		this.subject = subject;
		this.yearOfExperience = yearOfExperience;
		this.bio = bio;
	}

	@Override
	public String toString() {
		return "Faculty [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", currentAddress=" + currentAddress
				+ ", permanentAddress=" + permanentAddress + ", contact=" + contact + ", dateOfJoining=" + dateOfJoining
				+ ", level=" + level + ", subject=" + subject + ", yearOfExperience=" + yearOfExperience + ", bio="
				+ bio + "]";
	}

}
