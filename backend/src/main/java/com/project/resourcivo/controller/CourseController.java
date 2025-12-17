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
    private final com.project.resourcivo.service.IStudentService studentService;

    public CourseController(ICourseService service, com.project.resourcivo.service.IStudentService studentService) {
        this.service = service;
        this.studentService = studentService;
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

    @GetMapping("/{id}")
    @Operation(summary = "Get course by ID", description = "Retrieves a course by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course found"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    public ResponseEntity<?> getById(@PathVariable Long id) {
        var res = service.getById(id);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course", description = "Deletes a course by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    public ResponseEntity<?> delete(@Parameter(description = "Course ID") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/students")
    @Operation(summary = "Get students enrolled in course", description = "Retrieves a list of students enrolled in the specified course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of students retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    public ResponseEntity<?> getStudentsEnrolled(@Parameter(description = "Course ID") @PathVariable Long id) {
        CourseResponseDTO course = service.getById(id);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        // Assuming Student.course matches Course.name
        return ResponseEntity.ok(studentService.findByCourse(course.getName()));
    }
}
