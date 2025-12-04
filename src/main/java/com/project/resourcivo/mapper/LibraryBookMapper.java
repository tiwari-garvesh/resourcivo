package com.project.resourcivo.mapper;

import com.project.resourcivo.model.LibraryBook;
import com.project.resourcivo.dto.LibraryBookCreateDTO;
import com.project.resourcivo.dto.LibraryBookUpdateDTO;
import com.project.resourcivo.dto.LibraryBookResponseDTO;

public final class LibraryBookMapper {

    private LibraryBookMapper() {}

    public static LibraryBook toEntity(LibraryBookCreateDTO dto) {
        if (dto == null) return null;
        LibraryBook e = new LibraryBook();
        if (dto.getName() != null) e.setName(dto.getName());
        if (dto.getCategory() != null) e.setCategory(dto.getCategory());
        if (dto.getYearBought() != null) e.setYearBought(dto.getYearBought());
        if (dto.getAuthor() != null) e.setAuthor(dto.getAuthor());
        if (dto.getPrice() != null) e.setPrice(dto.getPrice());
        if (dto.getRating() != null) e.setRating(dto.getRating());
        if (dto.getReviews() != null) e.setReviews(dto.getReviews());
        if (dto.getAbout() != null) e.setAbout(dto.getAbout());
        if (dto.getIsIssued() != null) e.setIsIssued(dto.getIsIssued());
        return e;
    }

    public static void mergeUpdate(LibraryBookUpdateDTO dto, LibraryBook entity) {
        if (dto == null || entity == null) return;
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getCategory() != null) entity.setCategory(dto.getCategory());
        if (dto.getYearBought() != null) entity.setYearBought(dto.getYearBought());
        if (dto.getAuthor() != null) entity.setAuthor(dto.getAuthor());
        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getRating() != null) entity.setRating(dto.getRating());
        if (dto.getReviews() != null) entity.setReviews(dto.getReviews());
        if (dto.getAbout() != null) entity.setAbout(dto.getAbout());
        if (dto.getIsIssued() != null) entity.setIsIssued(dto.getIsIssued());
    }

    public static LibraryBookResponseDTO toResponse(LibraryBook e) {
        if (e == null) return null;
        LibraryBookResponseDTO r = new LibraryBookResponseDTO();
        r.setName(e.getName());
        r.setCategory(e.getCategory());
        r.setYearBought(e.getYearBought());
        r.setAuthor(e.getAuthor());
        r.setPrice(e.getPrice());
        r.setRating(e.getRating());
        r.setReviews(e.getReviews());
        r.setAbout(e.getAbout());
        r.setIsIssued(e.getIsIssued());
        return r;
    }
}
