package com.project.resourcivo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import com.project.resourcivo.repository.ContactRepository;
import com.project.resourcivo.mapper.ContactMapper;
import com.project.resourcivo.model.Contact;
import com.project.resourcivo.dto.ContactCreateDTO;
import com.project.resourcivo.dto.ContactUpdateDTO;
import com.project.resourcivo.dto.ContactResponseDTO;
import com.project.resourcivo.criteria.ContactFilterDTO;
import com.project.resourcivo.specification.ContactSpecification;

@Service
public class ContactService implements IContactService {

    private final ContactRepository repo;

    public ContactService(ContactRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public ContactResponseDTO createFromDto(ContactCreateDTO dto) {
        Contact e = ContactMapper.toEntity(dto);
        var saved = repo.save(e);
        return ContactMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ContactResponseDTO updateFromDto(Long id, ContactCreateDTO dto) {
        return repo.findById(id).map(existing -> {
            Contact updated = ContactMapper.toEntity(dto);
            // merge or replace existing fields as needed
            var s = repo.save(existing);
            return ContactMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    @Transactional
    public ContactResponseDTO partialUpdateFromDto(Long id, ContactUpdateDTO dto) {
        return repo.findById(id).map(existing -> {
            ContactMapper.mergeUpdate(dto, existing);
            var s = repo.save(existing);
            return ContactMapper.toResponse(s);
        }).orElse(null);
    }

    @Override
    public List<ContactResponseDTO> search(ContactFilterDTO filter) {
        return repo.findAll(ContactSpecification.build(filter)).stream().map(ContactMapper::toResponse).collect(Collectors.toList());
    }
}
