package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.AddressCreateDTO;
import com.project.resourcivo.dto.AddressUpdateDTO;
import com.project.resourcivo.dto.AddressResponseDTO;
import com.project.resourcivo.criteria.AddressFilterDTO;
import com.project.resourcivo.service.IAddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final IAddressService service;

    public AddressController(IAddressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> create(@Valid @RequestBody AddressCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/<built-in function id>")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody AddressCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/<built-in function id>")
    public ResponseEntity<?> patch(@PathVariable Long id, @Valid @RequestBody AddressUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    public ResponseEntity<List<AddressResponseDTO>> search(@RequestBody AddressFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }
}
