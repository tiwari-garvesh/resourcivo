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

@RestController
@RequestMapping("/api/librarybook")
public class LibraryBookController {

    private final ILibraryBookService service;

    public LibraryBookController(ILibraryBookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LibraryBookResponseDTO> create(@Valid @RequestBody LibraryBookCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/<built-in function id>")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody LibraryBookCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LibraryBook not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/<built-in function id>")
    public ResponseEntity<?> patch(@PathVariable Long id, @Valid @RequestBody LibraryBookUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LibraryBook not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    public ResponseEntity<List<LibraryBookResponseDTO>> search(@RequestBody LibraryBookFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }
}
