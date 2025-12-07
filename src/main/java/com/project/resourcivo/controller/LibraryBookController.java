package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.LibraryBookCreateDTO;
import com.project.resourcivo.dto.LibraryBookUpdateDTO;
import com.project.resourcivo.dto.LibraryBookResponseDTO;
import com.project.resourcivo.criteria.LibraryBookFilterDTO;
import com.project.resourcivo.service.ILibraryBookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/librarybook")
@Tag(name = "Library Book Management", description = "APIs for managing library books")
public class LibraryBookController {

    private final ILibraryBookService service;
    private final com.project.resourcivo.service.BookIssueService issueService;

    public LibraryBookController(ILibraryBookService service,
            com.project.resourcivo.service.BookIssueService issueService) {
        this.service = service;
        this.issueService = issueService;
    }

    @PostMapping
    @Operation(summary = "Create library book", description = "Creates a new library book")
    @ApiResponse(responseCode = "201", description = "Library book created successfully")
    public ResponseEntity<LibraryBookResponseDTO> create(@Valid @RequestBody LibraryBookCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update library book", description = "Updates an existing library book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Library book updated successfully"),
            @ApiResponse(responseCode = "404", description = "Library book not found")
    })
    public ResponseEntity<?> update(@Parameter(description = "Book ID") @PathVariable Long id,
            @Valid @RequestBody LibraryBookCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LibraryBook not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update library book", description = "Updates specific fields of a library book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Library book updated successfully"),
            @ApiResponse(responseCode = "404", description = "Library book not found")
    })
    public ResponseEntity<?> patch(@Parameter(description = "Book ID") @PathVariable Long id,
            @Valid @RequestBody LibraryBookUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LibraryBook not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    @Operation(summary = "Search library books", description = "Search library books using filter criteria")
    @ApiResponse(responseCode = "200", description = "Search results returned successfully")
    public ResponseEntity<List<LibraryBookResponseDTO>> search(@RequestBody LibraryBookFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }

    @GetMapping("/{id}/history")
    @Operation(summary = "Get book history", description = "Retrieves issue history for a specific book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "History retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<?> getBookHistory(@Parameter(description = "Book ID") @PathVariable Long id) {
        return ResponseEntity.ok(issueService.getBookHistory(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete library book", description = "Deletes a library book by ID")
    @ApiResponse(responseCode = "204", description = "Library book deleted successfully")
    public ResponseEntity<?> delete(@Parameter(description = "Book ID") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all library books", description = "Retrieves a list of all library books")
    @ApiResponse(responseCode = "200", description = "List of library books retrieved successfully")
    public ResponseEntity<List<LibraryBookResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
