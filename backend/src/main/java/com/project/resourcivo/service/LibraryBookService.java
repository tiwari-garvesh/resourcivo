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
    private final com.project.resourcivo.repository.LibraryRepository libraryRepo;

    public LibraryBookService(LibraryBookRepository repo,
            com.project.resourcivo.repository.LibraryRepository libraryRepo) {
        this.repo = repo;
        this.libraryRepo = libraryRepo;
    }

    @Override
    @Transactional
    public LibraryBookResponseDTO createFromDto(LibraryBookCreateDTO dto) {
        LibraryBook e = LibraryBookMapper.toEntity(dto);
        if (dto.getLibraryId() != null) {
            libraryRepo.findById(dto.getLibraryId()).ifPresent(e::setLibrary);
        }
        var saved = repo.save(e);
        return LibraryBookMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public LibraryBookResponseDTO updateFromDto(Long id, LibraryBookCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            // Update logic manually
            if (dto.getName() != null)
                existing.setName(dto.getName());
            if (dto.getCategory() != null)
                existing.setCategory(dto.getCategory());
            if (dto.getYearBought() != null)
                existing.setYearBought(dto.getYearBought());
            if (dto.getAuthor() != null)
                existing.setAuthor(dto.getAuthor());
            if (dto.getPrice() != null)
                existing.setPrice(dto.getPrice());
            if (dto.getRating() != null)
                existing.setRating(dto.getRating());
            if (dto.getReviews() != null)
                existing.setReviews(dto.getReviews());
            if (dto.getAbout() != null)
                existing.setAbout(dto.getAbout());
            if (dto.getIsIssued() != null)
                existing.setIsIssued(dto.getIsIssued());
            if (dto.getLibraryId() != null) {
                libraryRepo.findById(dto.getLibraryId()).ifPresent(existing::setLibrary);
            }

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
        return repo.findAll(LibraryBookSpecification.build(filter)).stream().map(LibraryBookMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
    }

    @Override
    public List<LibraryBookResponseDTO> getAll() {
        return repo.findAll().stream().map(LibraryBookMapper::toResponse).collect(Collectors.toList());
    }
}
