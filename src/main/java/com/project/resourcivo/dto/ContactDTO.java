package com.project.resourcivo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ContactDTO {

//    @Pattern(regexp = "^$|^[0-9+\-()\s]{7,20}$", message = "Invalid phone format")
    private String phone;

    @Email
    private String email;

    @Size(max = 200)
    private String other;

    // getters & setters
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getOther() { return other; }
    public void setOther(String other) { this.other = other; }
}
