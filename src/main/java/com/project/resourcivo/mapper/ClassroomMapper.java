package com.project.resourcivo.mapper;

import com.project.resourcivo.model.Classroom;
import com.project.resourcivo.dto.ClassroomCreateDTO;
import com.project.resourcivo.dto.ClassroomUpdateDTO;
import com.project.resourcivo.dto.ClassroomResponseDTO;

public final class ClassroomMapper {

    private ClassroomMapper() {}

    public static Classroom toEntity(ClassroomCreateDTO dto) {
        if (dto == null) return null;
        Classroom e = new Classroom();
        if (dto.getName() != null) e.setName(dto.getName());
        if (dto.getFloor() != null) e.setFloor(dto.getFloor());
        if (dto.getCapacity() != null) e.setCapacity(dto.getCapacity());
        if (dto.getHasProjector() != null) e.setHasProjector(dto.getHasProjector());
        if (dto.getHasSmartboard() != null) e.setHasSmartboard(dto.getHasSmartboard());
        if (dto.getIsAvailable() != null) e.setIsAvailable(dto.getIsAvailable());
        return e;
    }

    public static void mergeUpdate(ClassroomUpdateDTO dto, Classroom entity) {
        if (dto == null || entity == null) return;
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getFloor() != null) entity.setFloor(dto.getFloor());
        if (dto.getCapacity() != null) entity.setCapacity(dto.getCapacity());
        if (dto.getHasProjector() != null) entity.setHasProjector(dto.getHasProjector());
        if (dto.getHasSmartboard() != null) entity.setHasSmartboard(dto.getHasSmartboard());
        if (dto.getIsAvailable() != null) entity.setIsAvailable(dto.getIsAvailable());
    }

    public static ClassroomResponseDTO toResponse(Classroom e) {
        if (e == null) return null;
        ClassroomResponseDTO r = new ClassroomResponseDTO();
        r.setName(e.getName());
        r.setFloor(e.getFloor());
        r.setCapacity(e.getCapacity());
        r.setHasProjector(e.getHasProjector());
        r.setHasSmartboard(e.getHasSmartboard());
        r.setIsAvailable(e.getIsAvailable());
        return r;
    }
}
