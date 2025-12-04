package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.SubjectCreateDTO;
import com.project.resourcivo.dto.SubjectUpdateDTO;
import com.project.resourcivo.dto.SubjectResponseDTO;
import com.project.resourcivo.criteria.SubjectFilterDTO;
import com.project.resourcivo.service.ISubjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/subject")
@Tag(name = "Subject Management", description = "APIs for managing subjects")
public class SubjectController {

    private final ISubjectService service;

    public SubjectController(ISubjectService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new subject", description = "Creates a new subject with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subject created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<SubjectResponseDTO> create(@Valid @RequestBody SubjectCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update subject", description = "Updates all fields of an existing subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject updated successfully"),
            @ApiResponse(responseCode = "404", description = "Subject not found")
    })
    public ResponseEntity<?> update(
            @Parameter(description = "Subject ID") @PathVariable Long id,
            @Valid @RequestBody SubjectCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update subject", description = "Updates specific fields of an existing subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject updated successfully"),
            @ApiResponse(responseCode = "404", description = "Subject not found")
    })
    public ResponseEntity<?> patch(
            @Parameter(description = "Subject ID") @PathVariable Long id,
            @Valid @RequestBody SubjectUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    @Operation(summary = "Search subjects", description = "Search subjects using filter criteria")
    @ApiResponse(responseCode = "200", description = "Search results returned successfully")
    public ResponseEntity<List<SubjectResponseDTO>> search(@RequestBody SubjectFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }
}
