package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.CourseCreateDTO;
import com.project.resourcivo.dto.CourseUpdateDTO;
import com.project.resourcivo.dto.CourseResponseDTO;
import com.project.resourcivo.criteria.CourseFilterDTO;
import com.project.resourcivo.service.ICourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/course")
@Tag(name = "Course Management", description = "APIs for managing courses")
public class CourseController {

    private final ICourseService service;

    public CourseController(ICourseService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new course", description = "Creates a new course with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<CourseResponseDTO> create(@Valid @RequestBody CourseCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update course", description = "Updates all fields of an existing course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    public ResponseEntity<?> update(
            @Parameter(description = "Course ID") @PathVariable Long id,
            @Valid @RequestBody CourseCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update course", description = "Updates specific fields of an existing course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    public ResponseEntity<?> patch(
            @Parameter(description = "Course ID") @PathVariable Long id,
            @Valid @RequestBody CourseUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    @Operation(summary = "Search courses", description = "Search courses using filter criteria")
    @ApiResponse(responseCode = "200", description = "Search results returned successfully")
    public ResponseEntity<List<CourseResponseDTO>> search(@RequestBody CourseFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }
}
