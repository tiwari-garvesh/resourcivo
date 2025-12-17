package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.ClassroomCreateDTO;
import com.project.resourcivo.dto.ClassroomUpdateDTO;
import com.project.resourcivo.dto.ClassroomResponseDTO;
import com.project.resourcivo.criteria.ClassroomFilterDTO;
import com.project.resourcivo.service.IClassroomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/classroom")
@Tag(name = "Classroom Management", description = "APIs for managing classrooms")
public class ClassroomController {

    private final IClassroomService service;

    public ClassroomController(IClassroomService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create classroom", description = "Creates a new classroom")
    @ApiResponse(responseCode = "201", description = "Classroom created successfully")
    public ResponseEntity<ClassroomResponseDTO> create(@Valid @RequestBody ClassroomCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update classroom", description = "Updates all fields of an existing classroom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classroom updated successfully"),
            @ApiResponse(responseCode = "404", description = "Classroom not found")
    })
    public ResponseEntity<?> update(@Parameter(description = "Classroom ID") @PathVariable Long id,
            @Valid @RequestBody ClassroomCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classroom not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update classroom", description = "Updates specific fields of an existing classroom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classroom updated successfully"),
            @ApiResponse(responseCode = "404", description = "Classroom not found")
    })
    public ResponseEntity<?> patch(@Parameter(description = "Classroom ID") @PathVariable Long id,
            @Valid @RequestBody ClassroomUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classroom not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    @Operation(summary = "Search classrooms", description = "Search classrooms using filter criteria")
    @ApiResponse(responseCode = "200", description = "Search results returned successfully")
    public ResponseEntity<List<ClassroomResponseDTO>> search(@RequestBody ClassroomFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get classroom by ID", description = "Retrieves a classroom by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classroom found"),
            @ApiResponse(responseCode = "404", description = "Classroom not found")
    })
    public ResponseEntity<?> getById(@Parameter(description = "Classroom ID") @PathVariable Long id) {
        var res = service.getById(id);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classroom not found");
        return ResponseEntity.ok(res);
    }

    @GetMapping
    @Operation(summary = "Get all classrooms", description = "Retrieves a list of all classrooms")
    @ApiResponse(responseCode = "200", description = "List of classrooms retrieved successfully")
    public ResponseEntity<List<ClassroomResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete classroom", description = "Deletes a classroom by ID")
    @ApiResponse(responseCode = "204", description = "Classroom deleted successfully")
    public ResponseEntity<?> delete(@Parameter(description = "Classroom ID") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
