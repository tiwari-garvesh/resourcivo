package com.project.resourcivo.criteria;

import java.time.LocalDate;

public class FacultyFilterDTO {

    private String firstName;
    private String lastName;
    private String level;
    private String subject;
    private LocalDate dob;
    private Integer minExperience;
    private Integer maxExperience;

    // getters & setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public Integer getMinExperience() { return minExperience; }
    public void setMinExperience(Integer minExperience) { this.minExperience = minExperience; }
    public Integer getMaxExperience() { return maxExperience; }
    public void setMaxExperience(Integer maxExperience) { this.maxExperience = maxExperience; }
}
