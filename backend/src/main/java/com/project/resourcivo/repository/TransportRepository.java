package com.project.resourcivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.resourcivo.model.Transport;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long>, JpaSpecificationExecutor<Transport> {
    java.util.List<Transport> findByIsActive(Boolean isActive);

    java.util.Optional<Transport> findByRegistrationNumber(String registrationNumber);
}
