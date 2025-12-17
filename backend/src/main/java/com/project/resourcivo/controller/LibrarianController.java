package com.project.resourcivo.controller;

import com.project.resourcivo.dto.LibrarianCreateDTO;
import com.project.resourcivo.dto.LibrarianResponseDTO;
import com.project.resourcivo.dto.LibrarianUpdateDTO;
import com.project.resourcivo.service.ILibrarianService;
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
@RequestMapping("/api/librarian")
@Tag(name = "Librarian Management", description = "APIs for managing librarians")
public class LibrarianController {

    private final ILibrarianService service;
    private final com.project.resourcivo.service.BookIssueService issueService;

    public LibrarianController(ILibrarianService service,
            com.project.resourcivo.service.BookIssueService issueService) {
        this.service = service;
        this.issueService = issueService;
    }

    @PostMapping
    @Operation(summary = "Create librarian", description = "Creates a new librarian")
    @ApiResponse(responseCode = "201", description = "Librarian created successfully")
    public ResponseEntity<LibrarianResponseDTO> create(@Valid @RequestBody LibrarianCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update librarian", description = "Updates an existing librarian")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Librarian updated successfully"),
            @ApiResponse(responseCode = "404", description = "Librarian not found")
    })
    public ResponseEntity<?> update(@Parameter(description = "Librarian ID") @PathVariable Long id,
            @Valid @RequestBody LibrarianUpdateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Librarian not found");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get librarian by ID", description = "Retrieves a librarian by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Librarian found"),
            @ApiResponse(responseCode = "404", description = "Librarian not found")
    })
    public ResponseEntity<?> getById(@Parameter(description = "Librarian ID") @PathVariable Long id) {
        var res = service.getById(id);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Librarian not found");
        return ResponseEntity.ok(res);
    }

    @GetMapping
    @Operation(summary = "Get all librarians", description = "Retrieves a list of all librarians")
    @ApiResponse(responseCode = "200", description = "List of librarians retrieved successfully")
    public ResponseEntity<List<LibrarianResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/issue-requests")
    @Operation(summary = "Get pending issue requests", description = "Retrieves all book issue requests pending approval")
    @ApiResponse(responseCode = "200", description = "List of pending requests retrieved successfully")
    public ResponseEntity<?> getPendingRequests() {
        return ResponseEntity.ok(issueService.getAllPendingRequests());
    }

    @PutMapping("/issue-request/{id}/approve")
    @Operation(summary = "Approve issue request", description = "Approves a book issue request")
    @ApiResponse(responseCode = "200", description = "Request approved successfully")
    public ResponseEntity<?> approveIssue(@Parameter(description = "Request ID") @PathVariable Long id) {
        return ResponseEntity.ok(issueService.approveIssue(id));
    }

    @PutMapping("/issue-request/{id}/reject")
    @Operation(summary = "Reject issue request", description = "Rejects a book issue request")
    @ApiResponse(responseCode = "200", description = "Request rejected successfully")
    public ResponseEntity<?> rejectIssue(@Parameter(description = "Request ID") @PathVariable Long id) {
        return ResponseEntity.ok(issueService.rejectIssue(id));
    }

    @PutMapping("/issue-request/{id}/confirm-return")
    @Operation(summary = "Confirm book return", description = "Confirms that a book has been returned")
    @ApiResponse(responseCode = "200", description = "Return confirmed successfully")
    public ResponseEntity<?> confirmReturn(@Parameter(description = "Request ID") @PathVariable Long id) {
        return ResponseEntity.ok(issueService.confirmReturn(id));
    }

    @PutMapping("/issue-request/{id}/reject-return")
    @Operation(summary = "Reject book return", description = "Rejects a book return request")
    @ApiResponse(responseCode = "200", description = "Return rejected successfully")
    public ResponseEntity<?> rejectReturn(@Parameter(description = "Request ID") @PathVariable Long id) {
        return ResponseEntity.ok(issueService.rejectReturn(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete librarian", description = "Deletes a librarian by ID")
    @ApiResponse(responseCode = "204", description = "Librarian deleted successfully")
    public ResponseEntity<?> delete(@Parameter(description = "Librarian ID") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
