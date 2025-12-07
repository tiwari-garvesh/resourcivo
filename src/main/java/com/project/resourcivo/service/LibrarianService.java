package com.project.resourcivo.service;

import com.project.resourcivo.dto.LibrarianCreateDTO;
import com.project.resourcivo.dto.LibrarianResponseDTO;
import com.project.resourcivo.dto.LibrarianUpdateDTO;
import com.project.resourcivo.mapper.LibrarianMapper;
import com.project.resourcivo.model.Librarian;
import com.project.resourcivo.repository.LibrarianRepository;
import com.project.resourcivo.repository.LibraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibrarianService implements ILibrarianService {

    private final LibrarianRepository repo;
    private final LibraryRepository libraryRepo;

    public LibrarianService(LibrarianRepository repo, LibraryRepository libraryRepo) {
        this.repo = repo;
        this.libraryRepo = libraryRepo;
    }

    @Override
    @Transactional
    public LibrarianResponseDTO createFromDto(LibrarianCreateDTO dto) {
        Librarian e = LibrarianMapper.toEntity(dto);
        if (dto.getLibraryId() != null) {
            libraryRepo.findById(dto.getLibraryId()).ifPresent(e::setLibrary);
        }
        return LibrarianMapper.toResponse(repo.save(e));
    }

    @Override
    @Transactional
    public LibrarianResponseDTO updateFromDto(Long id, LibrarianUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            LibrarianMapper.mergeUpdate(dto, existing);
            if (dto.getLibraryId() != null) {
                libraryRepo.findById(dto.getLibraryId()).ifPresent(existing::setLibrary);
            }
            return LibrarianMapper.toResponse(repo.save(existing));
        }).orElse(null);
    }

    @Override
    public LibrarianResponseDTO getById(Long id) {
        return repo.findById(id).map(LibrarianMapper::toResponse).orElse(null);
    }

    @Override
    public List<LibrarianResponseDTO> getAll() {
        return repo.findAll().stream().map(LibrarianMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
    }
}
