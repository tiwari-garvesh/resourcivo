package com.project.resourcivo.dto;

import java.time.LocalDate;
import java.util.List;

public class FacultyResponseDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private AddressDTO currentAddress;
    private AddressDTO permanentAddress;
    private ContactDTO contact;
    private List<EmergencyContactDTO> emergencyContact;
    private String bio;
    private LocalDate dateOfJoining;
    private String level;
    private SubjectRefDTO subject;
    private Integer yearOfExperience;

    // getters & setters
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

    public AddressDTO getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(AddressDTO currentAddress) {
        this.currentAddress = currentAddress;
    }

    public AddressDTO getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(AddressDTO permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public ContactDTO getContact() {
        return contact;
    }

    public void setContact(ContactDTO contact) {
        this.contact = contact;
    }

    public List<EmergencyContactDTO> getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(List<EmergencyContactDTO> emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public SubjectRefDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectRefDTO subject) {
        this.subject = subject;
    }

    public void setSubjectId(Long subjectId) {
        if (this.subject == null)
            this.subject = new SubjectRefDTO();
        this.subject.setId(subjectId);
    }

    public Integer getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(Integer yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }
}
