package com.project.resourcivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.project.resourcivo.model.LabEquipment;

@Repository
public interface LabEquipmentRepository
        extends JpaRepository<LabEquipment, Long>, JpaSpecificationExecutor<LabEquipment> {

}
