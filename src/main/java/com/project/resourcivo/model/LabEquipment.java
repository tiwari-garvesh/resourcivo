package com.project.resourcivo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="labEquipment")
public class LabEquipment {
	
	@Id
	private Long id;

}
