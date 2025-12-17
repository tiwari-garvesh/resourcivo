package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.AddressCreateDTO;
import com.project.resourcivo.dto.AddressUpdateDTO;
import com.project.resourcivo.dto.AddressResponseDTO;
import com.project.resourcivo.criteria.AddressFilterDTO;
import com.project.resourcivo.service.IAddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/address")
@Tag(name = "Address Management", description = "APIs for managing addresses")
public class AddressController {

    private final IAddressService service;

    public AddressController(IAddressService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create address", description = "Creates a new address")
    @ApiResponse(responseCode = "201", description = "Address created successfully")
    public ResponseEntity<AddressResponseDTO> create(@Valid @RequestBody AddressCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update address", description = "Updates all fields of an existing address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    public ResponseEntity<?> update(@Parameter(description = "Address ID") @PathVariable Long id,
            @Valid @RequestBody AddressCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update address", description = "Updates specific fields of an existing address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    public ResponseEntity<?> patch(@Parameter(description = "Address ID") @PathVariable Long id,
            @Valid @RequestBody AddressUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    @Operation(summary = "Search addresses", description = "Search addresses using filter criteria")
    @ApiResponse(responseCode = "200", description = "Search results returned successfully")
    public ResponseEntity<List<AddressResponseDTO>> search(@RequestBody AddressFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get address by ID", description = "Retrieves an address by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address found"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    public ResponseEntity<?> getById(@Parameter(description = "Address ID") @PathVariable Long id) {
        var res = service.getById(id);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
        return ResponseEntity.ok(res);
    }
}
