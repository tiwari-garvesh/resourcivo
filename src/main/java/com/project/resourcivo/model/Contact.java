package com.project.resourcivo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Long primaryNumber;

	private Long alternateNumber;

	private String primaryEmail;

	private String alternateEmail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Long primaryNumber, Long alternateNumber, String primaryEmail, String alternateEmail) {
		super();
		this.primaryNumber = primaryNumber;
		this.alternateNumber = alternateNumber;
		this.primaryEmail = primaryEmail;
		this.alternateEmail = alternateEmail;
	}

	@Override
	public String toString() {
		return "Contact [primaryNumber=" + primaryNumber + ", alternateNumber=" + alternateNumber + ", primaryEmail="
				+ primaryEmail + ", alternateEmail=" + alternateEmail + "]";
	}

}
