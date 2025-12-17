package com.project.resourcivo.controller;

import com.project.resourcivo.dto.TransportManagerCreateDTO;
import com.project.resourcivo.dto.TransportManagerResponseDTO;
import com.project.resourcivo.dto.TransportManagerUpdateDTO;
import com.project.resourcivo.service.ITransportManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transport-manager")
@Tag(name = "Transport Manager Management", description = "APIs for managing transport managers")
public class TransportManagerController {

    private final ITransportManagerService service;

    public TransportManagerController(ITransportManagerService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create transport manager", description = "Creates a new transport manager")
    @ApiResponse(responseCode = "201", description = "Transport manager created successfully")
    @jakarta.annotation.security.RolesAllowed("ADMIN")
    public ResponseEntity<TransportManagerResponseDTO> create(@Valid @RequestBody TransportManagerCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update transport manager", description = "Updates an existing transport manager")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transport manager updated successfully"),
            @ApiResponse(responseCode = "404", description = "Transport manager not found")
    })
    @jakarta.annotation.security.RolesAllowed("ADMIN")
    public ResponseEntity<?> update(@Parameter(description = "Manager ID") @PathVariable Long id,
            @Valid @RequestBody TransportManagerUpdateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transport manager not found");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get transport manager by ID", description = "Retrieves a transport manager by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transport manager found"),
            @ApiResponse(responseCode = "404", description = "Transport manager not found")
    })
    @jakarta.annotation.security.RolesAllowed({ "ADMIN", "TRANSPORT_MANAGER" })
    public ResponseEntity<?> getById(@Parameter(description = "Manager ID") @PathVariable Long id) {
        var res = service.getById(id);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transport manager not found");
        return ResponseEntity.ok(res);
    }

    @GetMapping
    @Operation(summary = "Get all transport managers", description = "Retrieves a list of all transport managers")
    @jakarta.annotation.security.RolesAllowed({ "ADMIN", "TRANSPORT_MANAGER" })
    public ResponseEntity<List<TransportManagerResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete transport manager", description = "Deletes a transport manager by ID")
    @jakarta.annotation.security.RolesAllowed("ADMIN")
    public ResponseEntity<?> delete(@Parameter(description = "Manager ID") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
