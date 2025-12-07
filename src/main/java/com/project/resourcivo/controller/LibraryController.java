package com.project.resourcivo.controller;

import com.project.resourcivo.dto.LibraryCreateDTO;
import com.project.resourcivo.dto.LibraryResponseDTO;
import com.project.resourcivo.dto.LibraryUpdateDTO;
import com.project.resourcivo.service.ILibraryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/library")
@Tag(name = "Library Management", description = "APIs for managing libraries")
public class LibraryController {

    private final ILibraryService service;

    public LibraryController(ILibraryService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create library", description = "Creates a new library")
    @ApiResponse(responseCode = "201", description = "Library created successfully")
    public ResponseEntity<LibraryResponseDTO> create(@Valid @RequestBody LibraryCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update library", description = "Updates an existing library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Library updated successfully"),
            @ApiResponse(responseCode = "404", description = "Library not found")
    })
    public ResponseEntity<?> update(@Parameter(description = "Library ID") @PathVariable Long id,
            @Valid @RequestBody LibraryUpdateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Library not found");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get library by ID", description = "Retrieves a library by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Library found"),
            @ApiResponse(responseCode = "404", description = "Library not found")
    })
    public ResponseEntity<?> getById(@Parameter(description = "Library ID") @PathVariable Long id) {
        var res = service.getById(id);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Library not found");
        return ResponseEntity.ok(res);
    }

    @GetMapping
    @Operation(summary = "Get all libraries", description = "Retrieves a list of all libraries")
    @ApiResponse(responseCode = "200", description = "List of libraries retrieved successfully")
    public ResponseEntity<List<LibraryResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
