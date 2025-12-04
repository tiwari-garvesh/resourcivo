package com.project.resourcivo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transport")
public class Transport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String vehicleName; // e.g., College Bus, Van, Car

	private String vehicleNumber; // e.g., UP16 AB 1234)
	
	private String vehicleType; // Bus, Van, Car, Jeep, Auto etc.

	private String manufacturer;

	private LocalDate purchaseDate;

	private int seatingCapacity;

	private String driverName;

	@OneToOne
	private Contact driverContact;

	private String description;

	public Long getId() {
		return id;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Contact getDriverContact() {
		return driverContact;
	}

	public void setDriverContact(Contact driverContact) {
		this.driverContact = driverContact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Transport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transport(String vehicleName, String vehicleNumber, String vehicleType, String manufacturer,
			LocalDate purchaseDate, int seatingCapacity, String driverName, Contact driverContact, String description) {
		super();
		this.vehicleName = vehicleName;
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
		this.manufacturer = manufacturer;
		this.purchaseDate = purchaseDate;
		this.seatingCapacity = seatingCapacity;
		this.driverName = driverName;
		this.driverContact = driverContact;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Transport [id=" + id + ", vehicleName=" + vehicleName + ", vehicleNumber=" + vehicleNumber
				+ ", vehicleType=" + vehicleType + ", manufacturer=" + manufacturer + ", purchaseDate=" + purchaseDate
				+ ", seatingCapacity=" + seatingCapacity + ", driverName=" + driverName + ", driverContact="
				+ driverContact + ", description=" + description + "]";
	}
	
	

}
