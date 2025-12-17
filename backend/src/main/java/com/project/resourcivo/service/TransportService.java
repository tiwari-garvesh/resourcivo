package com.project.resourcivo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import com.project.resourcivo.repository.TransportRepository;
import com.project.resourcivo.mapper.TransportMapper;
import com.project.resourcivo.model.Transport;
import com.project.resourcivo.dto.TransportCreateDTO;
import com.project.resourcivo.dto.TransportUpdateDTO;
import com.project.resourcivo.dto.TransportResponseDTO;
import com.project.resourcivo.criteria.TransportFilterDTO;
import com.project.resourcivo.specification.TransportSpecification;

@Service
public class TransportService implements ITransportService {

    private final TransportRepository repo;

    public TransportService(TransportRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public TransportResponseDTO createFromDto(TransportCreateDTO dto) {
        Transport e = TransportMapper.toEntity(dto);
        var saved = repo.save(e);
        return TransportMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public TransportResponseDTO updateFromDto(Long id, TransportCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            // Update fields manually or via mapper helper - using direct update logic here
            // for clarity
            // Note: Ideally, TransportMapper should have an updateEntity from CreateDTO
            // method
            // Since we don't have that, we'll manually update key fields or create a new
            // mapping logic

            // For now, let's use a manual update approach for full update
            if (dto.getVehicleName() != null)
                existing.setVehicleName(dto.getVehicleName());
            if (dto.getRegistrationNumber() != null)
                existing.setRegistrationNumber(dto.getRegistrationNumber());
            if (dto.getVehicleType() != null)
                existing.setVehicleType(dto.getVehicleType());
            if (dto.getCompany() != null)
                existing.setCompany(dto.getCompany());
            if (dto.getColor() != null)
                existing.setColor(dto.getColor());
            if (dto.getParkingNumber() != null)
                existing.setParkingNumber(dto.getParkingNumber());
            if (dto.getRoutes() != null)
                existing.setRoutes(dto.getRoutes());
            if (dto.getPurchaseDate() != null)
                existing.setPurchaseDate(dto.getPurchaseDate());
            // Recalculate available seats based on the difference
            int oldTotal = existing.getTotalSeats();
            int newTotal = dto.getTotalSeats();
            int difference = newTotal - oldTotal;
            
            existing.setTotalSeats(newTotal);
            existing.setAvailableSeats(existing.getAvailableSeats() + difference);

            if (dto.getDriverName() != null)
                existing.setDriverName(dto.getDriverName());
            if (dto.getDescription() != null)
                existing.setDescription(dto.getDescription());
            if (dto.getIsActive() != null)
                existing.setIsActive(dto.getIsActive());

            var s = repo.save(existing);
            return TransportMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    public TransportResponseDTO partialUpdateFromDto(Long id, TransportUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            TransportMapper.mergeUpdate(dto, existing);
            var s = repo.save(existing);
            return TransportMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    public List<TransportResponseDTO> search(TransportFilterDTO filter) {
        return repo.findAll(TransportSpecification.build(filter)).stream().map(TransportMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TransportResponseDTO getById(Long id) {
        return repo.findById(id)
                .map(TransportMapper::toResponse)
                .orElse(null);
    }

    @Override
    public List<TransportResponseDTO> getAll() {
        return repo.findAll().stream()
                .map(TransportMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<TransportResponseDTO> getAvailableTransports() {
        return repo.findByIsActive(true).stream()
                .filter(t -> t.getAvailableSeats() > 0)
                .map(TransportMapper::toResponse)
                .collect(Collectors.toList());
    }
}
