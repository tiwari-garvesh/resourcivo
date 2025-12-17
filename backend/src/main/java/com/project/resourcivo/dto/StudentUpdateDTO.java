package com.project.resourcivo.dto;

import java.time.LocalDate;
import com.project.resourcivo.model.Course;

public class StudentUpdateDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private AddressCreateDTO currentAddress;
    private AddressCreateDTO permanentAddress;
    private ContactCreateDTO contact;
    private LocalDate dateOfJoining;
    private LocalDate yearOfGraduation;
    private Course course;
    private String bio;

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

    public AddressCreateDTO getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(AddressCreateDTO currentAddress) {
        this.currentAddress = currentAddress;
    }

    public AddressCreateDTO getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(AddressCreateDTO permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public ContactCreateDTO getContact() {
        return contact;
    }

    public void setContact(ContactCreateDTO contact) {
        this.contact = contact;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public LocalDate getYearOfGraduation() {
        return yearOfGraduation;
    }

    public void setYearOfGraduation(LocalDate yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
