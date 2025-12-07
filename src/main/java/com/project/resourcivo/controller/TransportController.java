package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.TransportCreateDTO;
import com.project.resourcivo.dto.TransportUpdateDTO;
import com.project.resourcivo.dto.TransportResponseDTO;
import com.project.resourcivo.criteria.TransportFilterDTO;
import com.project.resourcivo.service.ITransportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/transport")
@Tag(name = "Transport Management", description = "APIs for managing transport services")
public class TransportController {

    private final ITransportService service;

    public TransportController(ITransportService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create transport", description = "Creates a new transport service")
    @ApiResponse(responseCode = "201", description = "Transport created successfully")
    public ResponseEntity<TransportResponseDTO> create(@Valid @RequestBody TransportCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update transport", description = "Updates all fields of an existing transport service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transport updated successfully"),
            @ApiResponse(responseCode = "404", description = "Transport not found")
    })
    public ResponseEntity<?> update(@Parameter(description = "Transport ID") @PathVariable Long id,
            @Valid @RequestBody TransportCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transport not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update transport", description = "Updates specific fields of an existing transport service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transport updated successfully"),
            @ApiResponse(responseCode = "404", description = "Transport not found")
    })
    public ResponseEntity<?> patch(@Parameter(description = "Transport ID") @PathVariable Long id,
            @Valid @RequestBody TransportUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transport not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    @Operation(summary = "Search transport services", description = "Search transport services using filter criteria")
    @ApiResponse(responseCode = "200", description = "Search results returned successfully")
    public ResponseEntity<List<TransportResponseDTO>> search(@RequestBody TransportFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }
}
