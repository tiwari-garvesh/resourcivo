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

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final IContactService service;

    public ContactController(IContactService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> create(@Valid @RequestBody ContactCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/<built-in function id>")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ContactCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/<built-in function id>")
    public ResponseEntity<?> patch(@PathVariable Long id, @Valid @RequestBody ContactUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    public ResponseEntity<List<ContactResponseDTO>> search(@RequestBody ContactFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }
}
