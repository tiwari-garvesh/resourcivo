package com.project.resourcivo.dto;

import jakarta.validation.constraints.*;

public class TransportCreateDTO {
    @jakarta.validation.constraints.NotBlank(message = "Vehicle name is required")
    private String vehicleName;

    @jakarta.validation.constraints.NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @jakarta.validation.constraints.NotBlank(message = "Vehicle type is required")
    private String vehicleType;

    private String company;

    private String color;

    private String parkingNumber;

    private String routes;

    private java.time.LocalDate purchaseDate;

    @jakarta.validation.constraints.Min(value = 1, message = "Total seats must be at least 1")
    private int totalSeats;

    @jakarta.validation.constraints.NotBlank(message = "Driver name is required")
    private String driverName;

    private com.project.resourcivo.dto.ContactCreateDTO driverContact;

    private String description;

    private Boolean isActive = true;

    @NotNull(message = "Departure time is required")
    private java.time.LocalTime departureTime;

    @NotNull(message = "Arrival time is required")
    private java.time.LocalTime arrivalTime;

    @NotNull(message = "Return time is required")
    private java.time.LocalTime returnTime;

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

    public java.time.LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(java.time.LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public com.project.resourcivo.dto.ContactCreateDTO getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(com.project.resourcivo.dto.ContactCreateDTO driverContact) {
        this.driverContact = driverContact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
}
