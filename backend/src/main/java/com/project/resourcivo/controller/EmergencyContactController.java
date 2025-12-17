package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.EmergencyContactCreateDTO;
import com.project.resourcivo.dto.EmergencyContactUpdateDTO;
import com.project.resourcivo.dto.EmergencyContactResponseDTO;
import com.project.resourcivo.criteria.EmergencyContactFilterDTO;
import com.project.resourcivo.service.IEmergencyContactService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/emergencycontact")
@Tag(name = "Emergency Contact Management", description = "APIs for managing emergency contacts")
public class EmergencyContactController {

    private final IEmergencyContactService service;

    public EmergencyContactController(IEmergencyContactService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create emergency contact", description = "Creates a new emergency contact")
    @ApiResponse(responseCode = "201", description = "Emergency contact created successfully")
    public ResponseEntity<EmergencyContactResponseDTO> create(@Valid @RequestBody EmergencyContactCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update emergency contact", description = "Updates all fields of an existing emergency contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emergency contact updated successfully"),
            @ApiResponse(responseCode = "404", description = "Emergency contact not found")
    })
    public ResponseEntity<?> update(@Parameter(description = "Contact ID") @PathVariable Long id,
            @Valid @RequestBody EmergencyContactCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EmergencyContact not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update emergency contact", description = "Updates specific fields of an existing emergency contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emergency contact updated successfully"),
            @ApiResponse(responseCode = "404", description = "Emergency contact not found")
    })
    public ResponseEntity<?> patch(@Parameter(description = "Contact ID") @PathVariable Long id,
            @Valid @RequestBody EmergencyContactUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EmergencyContact not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    @Operation(summary = "Search emergency contacts", description = "Search emergency contacts using filter criteria")
    @ApiResponse(responseCode = "200", description = "Search results returned successfully")
    public ResponseEntity<List<EmergencyContactResponseDTO>> search(@RequestBody EmergencyContactFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get emergency contact by ID", description = "Retrieves an emergency contact by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emergency contact found"),
            @ApiResponse(responseCode = "404", description = "Emergency contact not found")
    })
    public ResponseEntity<?> getById(@Parameter(description = "Contact ID") @PathVariable Long id) {
        var res = service.getById(id);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EmergencyContact not found");
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete emergency contact", description = "Deletes an emergency contact by ID")
    @ApiResponse(responseCode = "204", description = "Emergency contact deleted successfully")
    public ResponseEntity<?> delete(@Parameter(description = "Contact ID") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
