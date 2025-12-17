package com.project.resourcivo.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@MappedSuperclass
public class BaseUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faculty_id")
	private Long id;

	private String firstName;

	private String middleName;

	private String lastName;

	private LocalDate dateOfBirth;
	
	private String gender;

	@OneToOne
	private Address currentAddress;

	@OneToOne
	private Address permanentAddress;

	@OneToOne
	private Contact contact;
	
	@OneToMany
	private List<EmergencyContact> emergencyContact;

	private String bio;

	public Long getId() {
		return id;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getBio() {
		return bio;
	}

	public List<EmergencyContact> getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(List<EmergencyContact> emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

}
