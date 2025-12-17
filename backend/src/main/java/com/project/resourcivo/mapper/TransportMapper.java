package com.project.resourcivo.mapper;

import com.project.resourcivo.model.Transport;
import com.project.resourcivo.dto.TransportCreateDTO;
import com.project.resourcivo.dto.TransportUpdateDTO;
import com.project.resourcivo.dto.TransportResponseDTO;

public final class TransportMapper {

    private TransportMapper() {
    }

    public static Transport toEntity(TransportCreateDTO dto) {
        if (dto == null)
            return null;
        Transport e = new Transport();
        if (dto.getVehicleName() != null)
            e.setVehicleName(dto.getVehicleName());
        if (dto.getRegistrationNumber() != null)
            e.setRegistrationNumber(dto.getRegistrationNumber());
        if (dto.getVehicleType() != null)
            e.setVehicleType(dto.getVehicleType());
        if (dto.getCompany() != null)
            e.setCompany(dto.getCompany());
        if (dto.getColor() != null)
            e.setColor(dto.getColor());
        if (dto.getParkingNumber() != null)
            e.setParkingNumber(dto.getParkingNumber());
        if (dto.getRoutes() != null)
            e.setRoutes(dto.getRoutes());
        if (dto.getPurchaseDate() != null)
            e.setPurchaseDate(dto.getPurchaseDate());
        e.setTotalSeats(dto.getTotalSeats());
        e.setAvailableSeats(dto.getTotalSeats()); // Initially all available
        if (dto.getDriverName() != null)
            e.setDriverName(dto.getDriverName());
        // resolve relation for driverContact by id or nested DTO
        if (dto.getDriverContact() != null) {
            // expecting nested DTO with id field or id property; try id resolution in
            // service
            // placeholder: set stub or fetch managed entity in service
        }
        if (dto.getDescription() != null)
            e.setDescription(dto.getDescription());
        if (dto.getIsActive() != null)
            e.setIsActive(dto.getIsActive());

        e.setDepartureTime(dto.getDepartureTime());
        e.setArrivalTime(dto.getArrivalTime());
        e.setReturnTime(dto.getReturnTime());
        return e;
    }

    public static void mergeUpdate(TransportUpdateDTO dto, Transport entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getVehicleName() != null)
            entity.setVehicleName(dto.getVehicleName());
        if (dto.getRegistrationNumber() != null)
            entity.setRegistrationNumber(dto.getRegistrationNumber());
        if (dto.getVehicleType() != null)
            entity.setVehicleType(dto.getVehicleType());
        if (dto.getCompany() != null)
            entity.setCompany(dto.getCompany());
        if (dto.getColor() != null)
            entity.setColor(dto.getColor());
        if (dto.getParkingNumber() != null)
            entity.setParkingNumber(dto.getParkingNumber());
        if (dto.getRoutes() != null)
            entity.setRoutes(dto.getRoutes());
        if (dto.getPurchaseDate() != null)
            entity.setPurchaseDate(dto.getPurchaseDate());
        if (dto.getTotalSeats() != null)
            entity.setTotalSeats(dto.getTotalSeats());
        if (dto.getDriverName() != null)
            entity.setDriverName(dto.getDriverName());
        if (dto.getDriverContact() != null) {
            // update relation: service should resolve and set managed Contact
        }
        if (dto.getDescription() != null)
            entity.setDescription(dto.getDescription());
        if (dto.getIsActive() != null)
            entity.setIsActive(dto.getIsActive());

        if (dto.getDepartureTime() != null)
            entity.setDepartureTime(dto.getDepartureTime());
        if (dto.getArrivalTime() != null)
            entity.setArrivalTime(dto.getArrivalTime());
        if (dto.getReturnTime() != null)
            entity.setReturnTime(dto.getReturnTime());
    }

    public static TransportResponseDTO toResponse(Transport e) {
        if (e == null)
            return null;
        TransportResponseDTO r = new TransportResponseDTO();
        r.setId(e.getId());
        r.setVehicleName(e.getVehicleName());
        r.setRegistrationNumber(e.getRegistrationNumber());
        r.setVehicleType(e.getVehicleType());
        r.setCompany(e.getCompany());
        r.setColor(e.getColor());
        r.setParkingNumber(e.getParkingNumber());
        r.setRoutes(e.getRoutes());
        r.setPurchaseDate(e.getPurchaseDate());
        r.setTotalSeats(e.getTotalSeats());
        r.setAvailableSeats(e.getAvailableSeats());
        r.setDriverName(e.getDriverName());
        r.setDriverName(e.getDriverName());
        if (e.getDriverContact() != null) {
            r.setDriverContactId(e.getDriverContact().getId());
            com.project.resourcivo.dto.ContactCreateDTO contactDto = new com.project.resourcivo.dto.ContactCreateDTO();
            contactDto.setPrimaryNumber(e.getDriverContact().getPrimaryNumber());
            contactDto.setAlternateNumber(e.getDriverContact().getAlternateNumber());
            contactDto.setPrimaryEmail(e.getDriverContact().getPrimaryEmail());
            contactDto.setAlternateEmail(e.getDriverContact().getAlternateEmail());
            r.setDriverContact(contactDto);
        }
        r.setDescription(e.getDescription());
        r.setIsActive(e.getIsActive());
        r.setDepartureTime(e.getDepartureTime());
        r.setArrivalTime(e.getArrivalTime());
        r.setReturnTime(e.getReturnTime());
        return r;
    }
}
