package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.InventoryItemCreateDTO;
import com.project.resourcivo.dto.InventoryItemUpdateDTO;
import com.project.resourcivo.dto.InventoryItemResponseDTO;
import com.project.resourcivo.criteria.InventoryItemFilterDTO;
import com.project.resourcivo.service.IInventoryItemService;

@RestController
@RequestMapping("/api/inventoryitem")
public class InventoryItemController {

    private final IInventoryItemService service;

    public InventoryItemController(IInventoryItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InventoryItemResponseDTO> create(@Valid @RequestBody InventoryItemCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/<built-in function id>")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody InventoryItemCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("InventoryItem not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/<built-in function id>")
    public ResponseEntity<?> patch(@PathVariable Long id, @Valid @RequestBody InventoryItemUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("InventoryItem not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    public ResponseEntity<List<InventoryItemResponseDTO>> search(@RequestBody InventoryItemFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }
}
