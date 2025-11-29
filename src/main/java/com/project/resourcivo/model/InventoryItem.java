package com.project.resourcivo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="inventoryItem")
public class InventoryItem {
	
	@Id
	private Long id;

}
