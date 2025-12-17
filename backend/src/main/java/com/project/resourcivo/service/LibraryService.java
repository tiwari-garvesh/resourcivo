package com.project.resourcivo.service;

import com.project.resourcivo.dto.LibraryCreateDTO;
import com.project.resourcivo.dto.LibraryResponseDTO;
import com.project.resourcivo.dto.LibraryUpdateDTO;
import com.project.resourcivo.mapper.LibraryMapper;
import com.project.resourcivo.model.Library;
import com.project.resourcivo.repository.LibraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService implements ILibraryService {

    private final LibraryRepository repo;

    public LibraryService(LibraryRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public LibraryResponseDTO createFromDto(LibraryCreateDTO dto) {
        Library e = LibraryMapper.toEntity(dto);
        return LibraryMapper.toResponse(repo.save(e));
    }

    @Override
    @Transactional
    public LibraryResponseDTO updateFromDto(Long id, LibraryUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            LibraryMapper.mergeUpdate(dto, existing);
            return LibraryMapper.toResponse(repo.save(existing));
        }).orElse(null);
    }

    @Override
    public LibraryResponseDTO getById(Long id) {
        return repo.findById(id).map(LibraryMapper::toResponse).orElse(null);
    }

    @Override
    public List<LibraryResponseDTO> getAll() {
        return repo.findAll().stream().map(LibraryMapper::toResponse).collect(Collectors.toList());
    }
}
