package com.project.resourcivo.repository;

import com.project.resourcivo.model.TransportManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportManagerRepository extends JpaRepository<TransportManager, Long> {
}
