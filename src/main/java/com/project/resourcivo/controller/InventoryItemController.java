package com.project.resourcivo.controller;

import com.project.resourcivo.dto.InventoryItemCreateDTO;
import com.project.resourcivo.dto.InventoryItemResponseDTO;
import com.project.resourcivo.service.InventoryItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-item")
@Tag(name = "Inventory Item", description = "Endpoints for managing consumable inventory items")
public class InventoryItemController {

    private final InventoryItemService service;

    public InventoryItemController(InventoryItemService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create Inventory Item", description = "Add new consumable item to inventory")
    public ResponseEntity<InventoryItemResponseDTO> create(@Valid @RequestBody InventoryItemCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Inventory Item by ID", description = "Retrieve item details by its ID")
    public ResponseEntity<InventoryItemResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get All Inventory Items", description = "Retrieve a list of all inventory items")
    public ResponseEntity<List<InventoryItemResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Inventory Item", description = "Update details of an existing item")
    public ResponseEntity<InventoryItemResponseDTO> update(@PathVariable Long id,
            @Valid @RequestBody InventoryItemCreateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial Update Inventory Item", description = "Update specific fields of an existing item")
    public ResponseEntity<InventoryItemResponseDTO> patch(@PathVariable Long id,
            @Valid @RequestBody InventoryItemCreateDTO dto) {
        return ResponseEntity.ok(service.partialUpdate(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Inventory Item", description = "Remove item from the inventory")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    @Operation(summary = "Search Inventory Items", description = "Search items using criteria")
    public ResponseEntity<List<InventoryItemResponseDTO>> search(
            @RequestBody com.project.resourcivo.criteria.InventoryItemFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }
}
