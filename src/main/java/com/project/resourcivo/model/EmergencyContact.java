package com.project.resourcivo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class EmergencyContact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private Long primaryContactNumber;

	private Long alternateContactNumber;

	private String email;

	@OneToOne
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrimaryContactNumber() {
		return primaryContactNumber;
	}

	public void setPrimaryContactNumber(Long primaryContactNumber) {
		this.primaryContactNumber = primaryContactNumber;
	}

	public Long getAlternateContactNumber() {
		return alternateContactNumber;
	}

	public void setAlternateContactNumber(Long alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EmergencyContact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmergencyContact(String name, Long primaryContactNumber, Long alternateContactNumber, String email,
			Address address) {
		super();
		this.name = name;
		this.primaryContactNumber = primaryContactNumber;
		this.alternateContactNumber = alternateContactNumber;
		this.email = email;
		this.address = address;
	}

	@Override
	public String toString() {
		return "EmergencyContact [name=" + name + ", primaryContactNumber=" + primaryContactNumber
				+ ", alternateContactNumber=" + alternateContactNumber + ", email=" + email + ", address=" + address
				+ "]";
	}

}
