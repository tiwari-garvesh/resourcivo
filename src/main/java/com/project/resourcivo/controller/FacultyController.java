package com.project.resourcivo.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.resourcivo.criteria.FacultyFilterDTO;
import com.project.resourcivo.dto.FacultyCreateDTO;
import com.project.resourcivo.dto.FacultyResponseDTO;
import com.project.resourcivo.dto.FacultyUpdateDTO;
import com.project.resourcivo.model.Faculty;
import com.project.resourcivo.service.IFacultyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/faculty")
@Tag(name = "Faculty Management", description = "APIs for managing faculty members")
public class FacultyController {

    private final IFacultyService facultyService;

    public FacultyController(IFacultyService facultyService) {
        this.facultyService = facultyService;
    }

    // ---------- DTO-based create
    @PostMapping
    @Operation(summary = "Create new faculty", description = "Creates a new faculty member with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Faculty created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<FacultyResponseDTO> createFaculty(@Valid @RequestBody FacultyCreateDTO dto) {
        FacultyResponseDTO created = facultyService.createFromDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ---------- DTO-based full update (PUT)
    @PutMapping("/{id}")
    @Operation(summary = "Update faculty", description = "Updates all fields of an existing faculty member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Faculty updated successfully"),
            @ApiResponse(responseCode = "404", description = "Faculty not found")
    })
    public ResponseEntity<?> updateFaculty(
            @Parameter(description = "Faculty ID") @PathVariable Long id,
            @Valid @RequestBody FacultyCreateDTO dto) {
        FacultyResponseDTO updated = facultyService.updateFromDto(id, dto);
        if (updated == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty not found");
        return ResponseEntity.ok(updated);
    }

    // ---------- DTO-based partial update (PATCH)
    @PatchMapping("/{id}")
    @Operation(summary = "Partial update faculty", description = "Updates specific fields of an existing faculty member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Faculty updated successfully"),
            @ApiResponse(responseCode = "404", description = "Faculty not found")
    })
    public ResponseEntity<?> patchFaculty(
            @Parameter(description = "Faculty ID") @PathVariable Long id,
            @Valid @RequestBody FacultyUpdateDTO dto) {
        FacultyResponseDTO updated = facultyService.partialUpdateFromDto(id, dto);
        if (updated == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty not found");
        return ResponseEntity.ok(updated);
    }

    // ---------- Search using filter DTO (specs)
    @PostMapping("/search")
    @Operation(summary = "Search faculty", description = "Search faculty members using filter criteria")
    @ApiResponse(responseCode = "200", description = "Search results returned successfully")
    public ResponseEntity<List<FacultyResponseDTO>> search(@RequestBody FacultyFilterDTO filter) {
        List<FacultyResponseDTO> list = facultyService.search(filter);
        return ResponseEntity.ok(list);
    }

    // ---------- Provide existing entity-based endpoints for backward compatibility
    // (optional)
    @PostMapping("/legacy/add")
    @Operation(summary = "Legacy: Add faculty", description = "Legacy endpoint for adding faculty (entity-based)")
    public ResponseEntity<Faculty> legacyAdd(@RequestBody Faculty faculty) {
        Faculty saved = facultyService.addFaculty(faculty);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get faculty by ID", description = "Retrieves a faculty member by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Faculty found"),
            @ApiResponse(responseCode = "404", description = "Faculty not found")
    })
    public ResponseEntity<?> getById(@Parameter(description = "Faculty ID") @PathVariable Long id) {
        Faculty f = facultyService.getFacultyById(id);
        if (f == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        return ResponseEntity.ok(f);
    }

    @GetMapping
    @Operation(summary = "Get all faculty", description = "Retrieves all faculty members")
    @ApiResponse(responseCode = "200", description = "Faculty list returned successfully")
    public ResponseEntity<List<Faculty>> getAll() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete faculty", description = "Deletes a faculty member by ID")
    @ApiResponse(responseCode = "200", description = "Faculty deleted successfully")
    public ResponseEntity<?> delete(@Parameter(description = "Faculty ID") @PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok("Deleted if existed");
    }
}
