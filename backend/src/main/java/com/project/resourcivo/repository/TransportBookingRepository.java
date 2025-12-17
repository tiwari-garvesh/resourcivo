package com.project.resourcivo.repository;

import com.project.resourcivo.model.BookingStatus;
import com.project.resourcivo.model.Role;
import com.project.resourcivo.model.TransportBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransportBookingRepository extends JpaRepository<TransportBooking, Long> {

    Optional<TransportBooking> findByUserIdAndUserTypeAndStatus(Long userId, Role.RoleName userType,
            BookingStatus status);

    List<TransportBooking> findByTransportIdAndStatus(Long transportId, BookingStatus status);

    List<TransportBooking> findByUserIdAndUserType(Long userId, Role.RoleName userType);

    boolean existsByUserIdAndUserTypeAndStatus(Long userId, Role.RoleName userType, BookingStatus status);
}
