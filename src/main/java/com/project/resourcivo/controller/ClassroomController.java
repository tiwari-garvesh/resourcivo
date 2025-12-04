package com.project.resourcivo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import com.project.resourcivo.dto.ClassroomCreateDTO;
import com.project.resourcivo.dto.ClassroomUpdateDTO;
import com.project.resourcivo.dto.ClassroomResponseDTO;
import com.project.resourcivo.criteria.ClassroomFilterDTO;
import com.project.resourcivo.service.IClassroomService;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

    private final IClassroomService service;

    public ClassroomController(IClassroomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClassroomResponseDTO> create(@Valid @RequestBody ClassroomCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFromDto(dto));
    }

    @PutMapping("/<built-in function id>")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ClassroomCreateDTO dto) {
        var res = service.updateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classroom not found");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/<built-in function id>")
    public ResponseEntity<?> patch(@PathVariable Long id, @Valid @RequestBody ClassroomUpdateDTO dto) {
        var res = service.partialUpdateFromDto(id, dto);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classroom not found");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/search")
    public ResponseEntity<List<ClassroomResponseDTO>> search(@RequestBody ClassroomFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }
}
