package com.project.resourcivo.controller;

import com.project.resourcivo.dto.LabEquipmentCreateDTO;
import com.project.resourcivo.dto.LabEquipmentResponseDTO;
import com.project.resourcivo.service.LabEquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab-equipment")
@Tag(name = "Lab Equipment", description = "Endpoints for managing Lab Equipment inventory")
public class LabEquipmentController {

    private final LabEquipmentService service;

    public LabEquipmentController(LabEquipmentService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create Lab Equipment", description = "Add new equipment to the laboratory inventory")
    public ResponseEntity<LabEquipmentResponseDTO> create(@Valid @RequestBody LabEquipmentCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Lab Equipment by ID", description = "Retrieve equipment details by its ID")
    public ResponseEntity<LabEquipmentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get All Lab Equipment", description = "Retrieve a list of all lab equipment")
    public ResponseEntity<List<LabEquipmentResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Lab Equipment", description = "Update details of an existing equipment")
    public ResponseEntity<LabEquipmentResponseDTO> update(@PathVariable Long id,
            @Valid @RequestBody LabEquipmentCreateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial Update Lab Equipment", description = "Update specific details of an existing equipment")
    public ResponseEntity<LabEquipmentResponseDTO> patch(@PathVariable Long id,
            @Valid @RequestBody LabEquipmentCreateDTO dto) {
        return ResponseEntity.ok(service.partialUpdate(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Lab Equipment", description = "Remove equipment from the inventory")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    @Operation(summary = "Search Lab Equipment", description = "Search equipment using criteria")
    public ResponseEntity<List<LabEquipmentResponseDTO>> search(
            @RequestBody com.project.resourcivo.criteria.LabEquipmentFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }
}
