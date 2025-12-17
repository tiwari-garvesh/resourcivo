package com.project.resourcivo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import com.project.resourcivo.repository.AddressRepository;
import com.project.resourcivo.mapper.AddressMapper;
import com.project.resourcivo.model.Address;
import com.project.resourcivo.dto.AddressCreateDTO;
import com.project.resourcivo.dto.AddressUpdateDTO;
import com.project.resourcivo.dto.AddressResponseDTO;
import com.project.resourcivo.criteria.AddressFilterDTO;
import com.project.resourcivo.specification.AddressSpecification;

@Service
public class AddressService implements IAddressService {

    private final AddressRepository repo;

    public AddressService(AddressRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public AddressResponseDTO createFromDto(AddressCreateDTO dto) {
        Address e = AddressMapper.toEntity(dto);
        var saved = repo.save(e);
        return AddressMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public AddressResponseDTO updateFromDto(Long id, AddressCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            AddressMapper.updateEntity(dto, existing);
            var s = repo.save(existing);
            return AddressMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    public AddressResponseDTO partialUpdateFromDto(Long id, AddressUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            AddressMapper.mergeUpdate(dto, existing);
            var s = repo.save(existing);
            return AddressMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    public AddressResponseDTO getById(Long id) {
        return repo.findById(id).map(AddressMapper::toResponse).orElse(null);
    }

    @Override
    public List<AddressResponseDTO> search(AddressFilterDTO filter) {
        return repo.findAll(AddressSpecification.build(filter)).stream().map(AddressMapper::toResponse)
                .collect(Collectors.toList());
    }
}
