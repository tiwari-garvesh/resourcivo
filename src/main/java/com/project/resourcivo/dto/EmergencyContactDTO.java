package com.project.resourcivo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmergencyContactDTO {

    @NotBlank
    @Size(max = 100)
    private String name;

//    @Pattern(regexp = "^[0-9+\-()\s]{7,20}$", message = "Invalid phone format")
    private String phone;

    @Size(max = 200)
    private String relation;

    // getters & setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getRelation() { return relation; }
    public void setRelation(String relation) { this.relation = relation; }
}
