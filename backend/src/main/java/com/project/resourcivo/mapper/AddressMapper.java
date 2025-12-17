package com.project.resourcivo.mapper;

import com.project.resourcivo.model.Address;
import com.project.resourcivo.dto.AddressCreateDTO;
import com.project.resourcivo.dto.AddressUpdateDTO;
import com.project.resourcivo.dto.AddressResponseDTO;

public final class AddressMapper {

    private AddressMapper() {
    }

    public static Address toEntity(AddressCreateDTO dto) {
        if (dto == null)
            return null;
        Address e = new Address();
        updateEntity(dto, e);
        return e;
    }

    public static void updateEntity(AddressCreateDTO dto, Address entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getHouseNumber() != null)
            entity.setHouseNumber(dto.getHouseNumber());
        if (dto.getAddressLine1() != null)
            entity.setAddressLine1(dto.getAddressLine1());
        if (dto.getAddressLine2() != null)
            entity.setAddressLine2(dto.getAddressLine2());
        if (dto.getCity() != null)
            entity.setCity(dto.getCity());
        if (dto.getState() != null)
            entity.setState(dto.getState());
        if (dto.getPincode() != null)
            entity.setPincode(dto.getPincode());
    }

    public static void mergeUpdate(AddressUpdateDTO dto, Address entity) {
        if (dto == null || entity == null)
            return;
        if (dto.getHouseNumber() != null)
            entity.setHouseNumber(dto.getHouseNumber());
        if (dto.getAddressLine1() != null)
            entity.setAddressLine1(dto.getAddressLine1());
        if (dto.getAddressLine2() != null)
            entity.setAddressLine2(dto.getAddressLine2());
        if (dto.getCity() != null)
            entity.setCity(dto.getCity());
        if (dto.getState() != null)
            entity.setState(dto.getState());
        if (dto.getPincode() != null)
            entity.setPincode(dto.getPincode());
    }

    public static AddressResponseDTO toResponse(Address e) {
        if (e == null)
            return null;
        AddressResponseDTO r = new AddressResponseDTO();
        r.setHouseNumber(e.getHouseNumber());
        r.setAddressLine1(e.getAddressLine1());
        r.setAddressLine2(e.getAddressLine2());
        r.setCity(e.getCity());
        r.setState(e.getState());
        r.setPincode(e.getPincode());
        return r;
    }
}
