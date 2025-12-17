package com.project.resourcivo.dto;

import jakarta.validation.constraints.NotNull;

public class TransportBookingRequestDTO {

    @NotNull(message = "Transport ID is required")
    private Long transportId;

    private String pickupPoint;

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }
}
