package com.project.resourcivo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Long primaryNumber;

	private Long alternateNumber;

	private String primaryEmail;

	private String alternateEmail;

	@OneToOne
	private EmergencyContact emergencyContact;

	public Long getPrimaryNumber() {
		return primaryNumber;
	}

	public void setPrimaryNumber(Long primaryNumber) {
		this.primaryNumber = primaryNumber;
	}

	public Long getAlternateNumber() {
		return alternateNumber;
	}

	public void setAlternateNumber(Long alternateNumber) {
		this.alternateNumber = alternateNumber;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public EmergencyContact getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(EmergencyContact emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Long primaryNumber, Long alternateNumber, String primaryEmail, String alternateEmail,
			EmergencyContact emergencyContact) {
		super();
		this.primaryNumber = primaryNumber;
		this.alternateNumber = alternateNumber;
		this.primaryEmail = primaryEmail;
		this.alternateEmail = alternateEmail;
		this.emergencyContact = emergencyContact;
	}

	@Override
	public String toString() {
		return "Contact [primaryNumber=" + primaryNumber + ", alternateNumber=" + alternateNumber + ", primaryEmail="
				+ primaryEmail + ", alternateEmail=" + alternateEmail + ", emergencyContact=" + emergencyContact + "]";
	}

}
