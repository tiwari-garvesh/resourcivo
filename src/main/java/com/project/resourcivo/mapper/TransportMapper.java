package com.project.resourcivo.mapper;

import com.project.resourcivo.model.Transport;
import com.project.resourcivo.dto.TransportCreateDTO;
import com.project.resourcivo.dto.TransportUpdateDTO;
import com.project.resourcivo.dto.TransportResponseDTO;

public final class TransportMapper {

    private TransportMapper() {}

    public static Transport toEntity(TransportCreateDTO dto) {
        if (dto == null) return null;
        Transport e = new Transport();
        if (dto.getVehicleName() != null) e.setVehicleName(dto.getVehicleName());
        if (dto.getVehicleNumber() != null) e.setVehicleNumber(dto.getVehicleNumber());
        if (dto.getVehicleType() != null) e.setVehicleType(dto.getVehicleType());
        if (dto.getManufacturer() != null) e.setManufacturer(dto.getManufacturer());
        if (dto.getPurchaseDate() != null) e.setPurchaseDate(dto.getPurchaseDate());
        if (dto.getSeatingCapacity() != null) e.setSeatingCapacity(dto.getSeatingCapacity());
        if (dto.getDriverName() != null) e.setDriverName(dto.getDriverName());
        // resolve relation for driverContact by id or nested DTO
        if (dto.getDriverContact() != null) {
            // expecting nested DTO with id field or id property; try id resolution in service
            // placeholder: set stub or fetch managed entity in service
        }
        // resolve relation for driverContact by id or nested DTO
        if (dto.getDriverContact() != null) {
            // expecting nested DTO with id field or id property; try id resolution in service
            // placeholder: set stub or fetch managed entity in service
        }
        if (dto.getDescription() != null) e.setDescription(dto.getDescription());
        return e;
    }

    public static void mergeUpdate(TransportUpdateDTO dto, Transport entity) {
        if (dto == null || entity == null) return;
        if (dto.getVehicleName() != null) entity.setVehicleName(dto.getVehicleName());
        if (dto.getVehicleNumber() != null) entity.setVehicleNumber(dto.getVehicleNumber());
        if (dto.getVehicleType() != null) entity.setVehicleType(dto.getVehicleType());
        if (dto.getManufacturer() != null) entity.setManufacturer(dto.getManufacturer());
        if (dto.getPurchaseDate() != null) entity.setPurchaseDate(dto.getPurchaseDate());
        if (dto.getSeatingCapacity() != null) entity.setSeatingCapacity(dto.getSeatingCapacity());
        if (dto.getDriverName() != null) entity.setDriverName(dto.getDriverName());
        if (dto.getDriverContact() != null) {
            // update relation: service should resolve and set managed Contact
        }
        if (dto.getDriverContact() != null) {
            // update relation: service should resolve and set managed Contact
        }
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
    }

    public static TransportResponseDTO toResponse(Transport e) {
        if (e == null) return null;
        TransportResponseDTO r = new TransportResponseDTO();
        r.setVehicleName(e.getVehicleName());
        r.setVehicleNumber(e.getVehicleNumber());
        r.setVehicleType(e.getVehicleType());
        r.setManufacturer(e.getManufacturer());
        r.setPurchaseDate(e.getPurchaseDate());
        r.setSeatingCapacity(e.getSeatingCapacity());
        r.setDriverName(e.getDriverName());
        if (e.getDriverContact() != null) r.setDriverContactId(e.getDriverContact().getId());
        if (e.getDriverContact() != null) r.setDriverContactId(e.getDriverContact().getId());
        r.setDescription(e.getDescription());
        return r;
    }
}
