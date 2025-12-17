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
@Table(name = "transport", indexes = {
		@Index(name = "idx_transport_registration", columnList = "registrationNumber", unique = true),
		@Index(name = "idx_transport_active", columnList = "isActive")
})
public class Transport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String vehicleName; // e.g., College Bus 1

	@Column(unique = true, nullable = false)
	private String registrationNumber; // changed from vehicleNumber

	private String vehicleType; // Bus, Van, Car, Jeep, etc.

	private String company; // changed from manufacturer

	private String color;

	private String parkingNumber;

	@Column(length = 1000)
	private String routes;

	private LocalDate purchaseDate;

	private int totalSeats; // changed from seatingCapacity

	private int availableSeats;

	private String driverName;

	@OneToOne
	private Contact driverContact;

	private String description;

	private java.time.LocalTime departureTime;

	private java.time.LocalTime arrivalTime;

	private java.time.LocalTime returnTime;

	private Boolean isActive = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getParkingNumber() {
		return parkingNumber;
	}

	public void setParkingNumber(String parkingNumber) {
		this.parkingNumber = parkingNumber;
	}

	public String getRoutes() {
		return routes;
	}

	public void setRoutes(String routes) {
		this.routes = routes;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
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

	public java.time.LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(java.time.LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public java.time.LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(java.time.LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public java.time.LocalTime getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(java.time.LocalTime returnTime) {
		this.returnTime = returnTime;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Transport() {
		super();
	}

	public Transport(String vehicleName, String registrationNumber, String vehicleType, String company, String color,
			String parkingNumber, String routes, LocalDate purchaseDate, int totalSeats, String driverName,
			Contact driverContact, String description) {
		super();
		this.vehicleName = vehicleName;
		this.registrationNumber = registrationNumber;
		this.vehicleType = vehicleType;
		this.company = company;
		this.color = color;
		this.parkingNumber = parkingNumber;
		this.routes = routes;
		this.purchaseDate = purchaseDate;
		this.totalSeats = totalSeats;
		this.availableSeats = totalSeats; // Initially all seats are available
		this.driverName = driverName;
		this.driverContact = driverContact;
		this.description = description;
		this.isActive = true;
	}

	@Override
	public String toString() {
		return "Transport [id=" + id + ", vehicleName=" + vehicleName + ", registrationNumber=" + registrationNumber
				+ ", vehicleType=" + vehicleType + ", company=" + company + ", color=" + color + ", parkingNumber="
				+ parkingNumber + ", totalSeats=" + totalSeats + ", availableSeats=" + availableSeats + ", isActive="
				+ isActive + "]";
	}

}
