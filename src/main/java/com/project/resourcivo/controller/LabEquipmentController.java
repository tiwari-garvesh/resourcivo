package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.LabEquipmentCreateDTO;
import com.project.resourcivo.dto.LabEquipmentUpdateDTO;
import com.project.resourcivo.dto.LabEquipmentResponseDTO;
import com.project.resourcivo.criteria.LabEquipmentFilterDTO;
import com.project.resourcivo.service.ILabEquipmentService;

@RestController
@RequestMapping("/api/labequipment")
public class LabEquipmentController {

    private final ILabEquipmentService service;

    public LabEquipmentController(ILabEquipmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LabEquipmentResponseDTO> create(@Valid @RequestBody LabEquipmentCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/<built-in function id>")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody LabEquipmentCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LabEquipment not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/<built-in function id>")
    public ResponseEntity<?> patch(@PathVariable Long id, @Valid @RequestBody LabEquipmentUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LabEquipment not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    public ResponseEntity<List<LabEquipmentResponseDTO>> search(@RequestBody LabEquipmentFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }
}
