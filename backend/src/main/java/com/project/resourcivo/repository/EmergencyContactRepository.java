package com.project.resourcivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.project.resourcivo.model.EmergencyContact;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long>, JpaSpecificationExecutor<EmergencyContact> {

}
