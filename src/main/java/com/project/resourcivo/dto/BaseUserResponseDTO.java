package com.project.resourcivo.dto;

import java.time.LocalDate;
import java.util.List;
import com.project.resourcivo.model.EmergencyContact;

public class BaseUserResponseDTO {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private Long currentAddressId;
    private Long permanentAddressId;
    private Integer contactId;
    private List<EmergencyContact> emergencyContact;
    private String bio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCurrentAddressId() {
        return currentAddressId;
    }

    public void setCurrentAddressId(Long currentAddressId) {
        this.currentAddressId = currentAddressId;
    }

    public Long getPermanentAddressId() {
        return permanentAddressId;
    }

    public void setPermanentAddressId(Long permanentAddressId) {
        this.permanentAddressId = permanentAddressId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public List<EmergencyContact> getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(List<EmergencyContact> emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
