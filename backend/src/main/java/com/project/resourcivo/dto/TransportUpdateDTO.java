package com.project.resourcivo.dto;

import java.time.LocalDate;

public class TransportUpdateDTO {
    private String vehicleName;
    private String registrationNumber;
    private String vehicleType;
    private String company;
    private String color;
    private String parkingNumber;
    private String routes;
    private LocalDate purchaseDate;
    private Integer totalSeats;
    private String driverName;
    private ContactRefDTO driverContact;
    private String description;
    private Boolean isActive;

    private java.time.LocalTime departureTime;
    private java.time.LocalTime arrivalTime;
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

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public ContactRefDTO getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(ContactRefDTO driverContact) {
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
