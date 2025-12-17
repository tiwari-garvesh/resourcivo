package com.project.resourcivo.service;

import com.project.resourcivo.dto.TransportBookingRequestDTO;
import com.project.resourcivo.dto.TransportBookingResponseDTO;
import com.project.resourcivo.mapper.TransportBookingMapper;
import com.project.resourcivo.model.BookingStatus;
import com.project.resourcivo.model.Role;
import com.project.resourcivo.model.Transport;
import com.project.resourcivo.model.TransportBooking;
import com.project.resourcivo.repository.TransportBookingRepository;
import com.project.resourcivo.repository.TransportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportBookingService implements ITransportBookingService {

    private final TransportBookingRepository bookingRepository;
    private final TransportRepository transportRepository;

    public TransportBookingService(TransportBookingRepository bookingRepository,
            TransportRepository transportRepository) {
        this.bookingRepository = bookingRepository;
        this.transportRepository = transportRepository;
    }

    @Override
    @Transactional
    public TransportBookingResponseDTO bookTransport(Long userId, Role.RoleName userType,
            TransportBookingRequestDTO dto) {
        // 1. Check if user already has an active booking
        if (bookingRepository.existsByUserIdAndUserTypeAndStatus(userId, userType, BookingStatus.ACTIVE)) {
            throw new IllegalStateException("User already has an active transport booking");
        }

        // 2. Fetch transport and check availability
        Transport transport = transportRepository.findById(dto.getTransportId())
                .orElseThrow(() -> new IllegalArgumentException("Transport not found"));

        if (!transport.getIsActive()) {
            throw new IllegalArgumentException("Transport is not currently active");
        }

        if (transport.getAvailableSeats() <= 0) {
            throw new IllegalStateException("No seats available on this transport");
        }

        // 3. Create booking
        TransportBooking booking = TransportBookingMapper.toEntity(dto, transport, userId, userType);
        booking.setStatus(BookingStatus.ACTIVE);

        // 4. Update transport avaialble seats
        transport.setAvailableSeats(transport.getAvailableSeats() - 1);
        transportRepository.save(transport);

        // 5. Save booking
        TransportBooking savedBooking = bookingRepository.save(booking);

        return TransportBookingMapper.toResponse(savedBooking);
    }

    @Override
    @Transactional
    public void cancelBooking(Long userId, Role.RoleName userType) {
        TransportBooking booking = bookingRepository
                .findByUserIdAndUserTypeAndStatus(userId, userType, BookingStatus.ACTIVE)
                .orElseThrow(() -> new IllegalArgumentException("No active transport booking found for this user"));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        // Restore seat availability
        Transport transport = booking.getTransport();
        transport.setAvailableSeats(transport.getAvailableSeats() + 1);
        transportRepository.save(transport);
    }

    @Override
    public TransportBookingResponseDTO getMyBooking(Long userId, Role.RoleName userType) {
        return bookingRepository.findByUserIdAndUserTypeAndStatus(userId, userType, BookingStatus.ACTIVE)
                .map(TransportBookingMapper::toResponse)
                .orElse(null);
    }

    @Override
    public List<TransportBookingResponseDTO> getBookingsForTransport(Long transportId) {
        return bookingRepository.findByTransportIdAndStatus(transportId, BookingStatus.ACTIVE)
                .stream()
                .map(TransportBookingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransportBookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(TransportBookingMapper::toResponse)
                .collect(Collectors.toList());
    }
}
