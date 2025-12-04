package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.EmergencyContactCreateDTO;
import com.project.resourcivo.dto.EmergencyContactUpdateDTO;
import com.project.resourcivo.dto.EmergencyContactResponseDTO;
import com.project.resourcivo.criteria.EmergencyContactFilterDTO;
import com.project.resourcivo.service.IEmergencyContactService;

@RestController
@RequestMapping("/api/emergencycontact")
public class EmergencyContactController {

    private final IEmergencyContactService service;

    public EmergencyContactController(IEmergencyContactService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmergencyContactResponseDTO> create(@Valid @RequestBody EmergencyContactCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/<built-in function id>")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody EmergencyContactCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EmergencyContact not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/<built-in function id>")
    public ResponseEntity<?> patch(@PathVariable Long id, @Valid @RequestBody EmergencyContactUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EmergencyContact not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    public ResponseEntity<List<EmergencyContactResponseDTO>> search(@RequestBody EmergencyContactFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }
}
