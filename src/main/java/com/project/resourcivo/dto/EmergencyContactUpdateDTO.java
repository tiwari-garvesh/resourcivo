package com.project.resourcivo.dto;

public class EmergencyContactUpdateDTO {
    private String name;
    private Long primaryContactNumber;
    private Long alternateContactNumber;
    private String email;
    private AddressCreateDTO address;

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

    public AddressCreateDTO getAddress() {
        return address;
    }

    public void setAddress(AddressCreateDTO address) {
        this.address = address;
    }
}
