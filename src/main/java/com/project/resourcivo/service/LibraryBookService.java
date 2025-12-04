package com.project.resourcivo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import com.project.resourcivo.repository.LibraryBookRepository;
import com.project.resourcivo.mapper.LibraryBookMapper;
import com.project.resourcivo.model.LibraryBook;
import com.project.resourcivo.dto.LibraryBookCreateDTO;
import com.project.resourcivo.dto.LibraryBookUpdateDTO;
import com.project.resourcivo.dto.LibraryBookResponseDTO;
import com.project.resourcivo.criteria.LibraryBookFilterDTO;
import com.project.resourcivo.specification.LibraryBookSpecification;

@Service
public class LibraryBookService implements ILibraryBookService {

    private final LibraryBookRepository repo;

    public LibraryBookService(LibraryBookRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public LibraryBookResponseDTO createFromDto(LibraryBookCreateDTO dto) {
        LibraryBook e = LibraryBookMapper.toEntity(dto);
        var saved = repo.save(e);
        return LibraryBookMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public LibraryBookResponseDTO updateFromDto(Long id, LibraryBookCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            LibraryBook updated = LibraryBookMapper.toEntity(dto);
            // merge or replace existing fields as needed
            var s = repo.save(existing);
            return LibraryBookMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    public LibraryBookResponseDTO partialUpdateFromDto(Long id, LibraryBookUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            LibraryBookMapper.mergeUpdate(dto, existing);
            var s = repo.save(existing);
            return LibraryBookMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    public List<LibraryBookResponseDTO> search(LibraryBookFilterDTO filter) {
        return repo.findAll(LibraryBookSpecification.build(filter)).stream().map(LibraryBookMapper::toResponse).collect(Collectors.toList());
    }
}
