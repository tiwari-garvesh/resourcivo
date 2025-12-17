package com.project.resourcivo.dto;

import jakarta.validation.constraints.*;

public class ContactCreateDTO {
    @NotNull(message = "Primary number is required")
    @Min(value = 1000000000L, message = "Primary number must be 10 digits")
    @Max(value = 9999999999L, message = "Primary number must be 10 digits")
    private Long primaryNumber;

    @Min(value = 1000000000L, message = "Alternate number must be 10 digits")
    @Max(value = 9999999999L, message = "Alternate number must be 10 digits")
    private Long alternateNumber;

    @NotBlank(message = "Primary email is required")
    @Email(message = "Primary email must be valid")
    private String primaryEmail;

    @Email(message = "Alternate email must be valid")
    private String alternateEmail;

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
