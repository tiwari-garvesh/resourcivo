package com.project.resourcivo.dto;

public class EmergencyContactResponseDTO {
    private Long id;
    private String name;
    private Long primaryContactNumber;
    private Long alternateContactNumber;
    private String email;
    private Long addressId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
