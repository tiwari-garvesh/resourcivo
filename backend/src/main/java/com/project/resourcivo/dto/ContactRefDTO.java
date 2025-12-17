package com.project.resourcivo.dto;

public class ContactRefDTO {
    private Integer id;
    private Long primaryNumber;
    private String primaryEmail;

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

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }
}
