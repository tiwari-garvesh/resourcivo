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
    private final com.project.resourcivo.service.ITransportBookingService bookingService;

    public TransportController(ITransportService service,
            com.project.resourcivo.service.ITransportBookingService bookingService) {
        this.service = service;
        this.bookingService = bookingService;
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

    @GetMapping
    @Operation(summary = "Get all transports", description = "Retrieve all transport services")
    public ResponseEntity<List<TransportResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get transport by ID", description = "Retrieve a specific transport service by ID")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        var res = service.getById(id);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transport not found");
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete transport", description = "Delete a transport service by ID")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    @Operation(summary = "Get available transports", description = "Returns active transports with available seats")
    public ResponseEntity<List<TransportResponseDTO>> getAvailableTransports() {
        return ResponseEntity.ok(service.getAvailableTransports());
    }

    // --- Booking Endpoints ---

    @PostMapping("/booking/student/{studentId}")
    @Operation(summary = "Student books transport", description = "Allows a student to book a transport seat")
    public ResponseEntity<?> studentBookTransport(@PathVariable Long studentId,
            @Valid @RequestBody com.project.resourcivo.dto.TransportBookingRequestDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(bookingService.bookTransport(studentId,
                            com.project.resourcivo.model.Role.RoleName.ROLE_STUDENT, dto));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new com.project.resourcivo.exception.ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(),
                            java.time.LocalDateTime.now()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    new com.project.resourcivo.exception.ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
                            java.time.LocalDateTime.now()));
        }
    }

    @GetMapping("/booking/student/{studentId}")
    @Operation(summary = "Get student's booking", description = "Get current active booking for a student")
    public ResponseEntity<?> getStudentBooking(@PathVariable Long studentId) {
        var res = bookingService.getMyBooking(studentId, com.project.resourcivo.model.Role.RoleName.ROLE_STUDENT);
        if (res == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/booking/student/{studentId}")
    @Operation(summary = "Cancel student's booking", description = "Cancel active booking for a student")
    public ResponseEntity<?> cancelStudentBooking(@PathVariable Long studentId) {
        try {
            bookingService.cancelBooking(studentId, com.project.resourcivo.model.Role.RoleName.ROLE_STUDENT);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/booking/faculty/{facultyId}")
    @Operation(summary = "Faculty books transport", description = "Allows a faculty to book a transport seat")
    public ResponseEntity<?> facultyBookTransport(@PathVariable Long facultyId,
            @Valid @RequestBody com.project.resourcivo.dto.TransportBookingRequestDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(bookingService.bookTransport(facultyId,
                            com.project.resourcivo.model.Role.RoleName.ROLE_FACULTY, dto));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new com.project.resourcivo.exception.ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(),
                            java.time.LocalDateTime.now()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    new com.project.resourcivo.exception.ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
                            java.time.LocalDateTime.now()));
        }
    }

    @GetMapping("/booking/faculty/{facultyId}")
    @Operation(summary = "Get faculty's booking", description = "Get current active booking for a faculty")
    public ResponseEntity<?> getFacultyBooking(@PathVariable Long facultyId) {
        var res = bookingService.getMyBooking(facultyId, com.project.resourcivo.model.Role.RoleName.ROLE_FACULTY);
        if (res == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/booking/faculty/{facultyId}")
    @Operation(summary = "Cancel faculty's booking", description = "Cancel active booking for a faculty")
    public ResponseEntity<?> cancelFacultyBooking(@PathVariable Long facultyId) {
        try {
            bookingService.cancelBooking(facultyId, com.project.resourcivo.model.Role.RoleName.ROLE_FACULTY);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/bookings")
    @Operation(summary = "Get bookings for transport", description = "Get all active bookings for a specific transport")
    public ResponseEntity<List<com.project.resourcivo.dto.TransportBookingResponseDTO>> getTransportBookings(
            @PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingsForTransport(id));
    }

    @GetMapping("/bookings")
    @Operation(summary = "Get all bookings", description = "Get all transport bookings (Admin/Manager Overview)")
    @jakarta.annotation.security.RolesAllowed({ "ADMIN", "TRANSPORT_MANAGER" })
    public ResponseEntity<List<com.project.resourcivo.dto.TransportBookingResponseDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}
