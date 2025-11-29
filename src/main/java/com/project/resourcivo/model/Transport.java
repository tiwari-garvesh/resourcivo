package com.project.resourcivo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transport")
public class Transport {
	
	@Id
	private Long id;

}
