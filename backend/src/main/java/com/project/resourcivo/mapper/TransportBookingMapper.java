package com.project.resourcivo.mapper;

import com.project.resourcivo.dto.TransportBookingRequestDTO;
import com.project.resourcivo.dto.TransportBookingResponseDTO;
import com.project.resourcivo.model.Role;
import com.project.resourcivo.model.Transport;
import com.project.resourcivo.model.TransportBooking;

public final class TransportBookingMapper {

    private TransportBookingMapper() {
    }

    public static TransportBooking toEntity(TransportBookingRequestDTO dto, Transport transport, Long userId,
            Role.RoleName userType) {
        if (dto == null)
            return null;

        TransportBooking booking = new TransportBooking();
        booking.setTransport(transport);
        booking.setUserId(userId);
        booking.setUserType(userType);
        if (dto.getPickupPoint() != null) {
            booking.setPickupPoint(dto.getPickupPoint());
        }
        return booking;
    }

    public static TransportBookingResponseDTO toResponse(TransportBooking entity) {
        if (entity == null)
            return null;

        TransportBookingResponseDTO dto = new TransportBookingResponseDTO();
        dto.setId(entity.getId());
        dto.setTransport(TransportMapper.toResponse(entity.getTransport()));
        dto.setUserId(entity.getUserId());
        dto.setUserType(entity.getUserType().name());
        dto.setBookingDate(entity.getBookingDate());
        dto.setStatus(entity.getStatus().name());
        dto.setPickupPoint(entity.getPickupPoint());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }
}
