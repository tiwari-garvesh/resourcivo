package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.ContactCreateDTO;
import com.project.resourcivo.dto.ContactUpdateDTO;
import com.project.resourcivo.dto.ContactResponseDTO;
import com.project.resourcivo.criteria.ContactFilterDTO;
import com.project.resourcivo.service.IContactService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/contact")
@Tag(name = "Contact Management", description = "APIs for managing contact information")
public class ContactController {

    private final IContactService service;

    public ContactController(IContactService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create contact", description = "Creates a new contact")
    @ApiResponse(responseCode = "201", description = "Contact created successfully")
    public ResponseEntity<ContactResponseDTO> create(@Valid @RequestBody ContactCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update contact", description = "Updates all fields of an existing contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact updated successfully"),
            @ApiResponse(responseCode = "404", description = "Contact not found")
    })
    public ResponseEntity<?> update(@Parameter(description = "Contact ID") @PathVariable Long id,
            @Valid @RequestBody ContactCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update contact", description = "Updates specific fields of an existing contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact updated successfully"),
            @ApiResponse(responseCode = "404", description = "Contact not found")
    })
    public ResponseEntity<?> patch(@Parameter(description = "Contact ID") @PathVariable Long id,
            @Valid @RequestBody ContactUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    @Operation(summary = "Search contacts", description = "Search contacts using filter criteria")
    @ApiResponse(responseCode = "200", description = "Search results returned successfully")
    public ResponseEntity<List<ContactResponseDTO>> search(@RequestBody ContactFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get contact by ID", description = "Retrieves a contact by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact found"),
            @ApiResponse(responseCode = "404", description = "Contact not found")
    })
    public ResponseEntity<?> getById(@Parameter(description = "Contact ID") @PathVariable Long id) {
        var res = service.getById(id);
        if (res == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
        return ResponseEntity.ok(res);
    }
}
