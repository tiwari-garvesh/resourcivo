package com.project.resourcivo.service;

import com.project.resourcivo.dto.TransportBookingRequestDTO;
import com.project.resourcivo.dto.TransportBookingResponseDTO;
import com.project.resourcivo.model.Role;

import java.util.List;

public interface ITransportBookingService {
    TransportBookingResponseDTO bookTransport(Long userId, Role.RoleName userType, TransportBookingRequestDTO dto);

    void cancelBooking(Long userId, Role.RoleName userType);

    TransportBookingResponseDTO getMyBooking(Long userId, Role.RoleName userType);

    List<TransportBookingResponseDTO> getBookingsForTransport(Long transportId);

    List<TransportBookingResponseDTO> getAllBookings();
}
