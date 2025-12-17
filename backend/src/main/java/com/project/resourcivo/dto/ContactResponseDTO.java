package com.project.resourcivo.dto;

public class ContactResponseDTO {
    private Integer id;
    private Long primaryNumber;
    private Long alternateNumber;
    private String primaryEmail;
    private String alternateEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPrimaryNumber() {
        return primaryNumber;
    }

    public void setPrimaryNumber(Long primaryNumber) {
        this.primaryNumber = primaryNumber;
    }

    public Long getAlternateNumber() {
        return alternateNumber;
    }

    public void setAlternateNumber(Long alternateNumber) {
        this.alternateNumber = alternateNumber;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }
}
