package com.project.resourcivo.dto;

import java.time.LocalDate;
import com.project.resourcivo.model.Course;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class StudentCreateDTO {
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters")
    private String firstName;

    @Size(max = 100, message = "Middle name must not exceed 100 characters")
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Valid
    private AddressCreateDTO currentAddress;

    @Valid
    private AddressCreateDTO permanentAddress;

    @Valid
    private ContactCreateDTO contact;

    private LocalDate dateOfJoining;
    private LocalDate yearOfGraduation;
    private Course course;

    @Size(max = 1000, message = "Bio must not exceed 1000 characters")
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
