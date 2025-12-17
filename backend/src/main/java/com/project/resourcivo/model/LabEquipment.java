package com.project.resourcivo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lab_inventory")
public class LabEquipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@jakarta.validation.constraints.NotBlank
	private String name;

	@jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
	private LabType labType;

	private String category;

	// Inventory
	private int totalQuantity;
	private int availableQuantity;

	@jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
	private EquipmentStatus status;

	private String conditionDetails; // "New", "Good", etc.

	// Details
	private String modelNumber;
	private String description;

	// Maintenance
	private LocalDate lastMaintenanceDate;
	private LocalDate nextMaintenanceDueDate;

	// Location
	private String aisleNumber;
	private String shelfNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LabType getLabType() {
		return labType;
	}

	public void setLabType(LabType labType) {
		this.labType = labType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public EquipmentStatus getStatus() {
		return status;
	}

	public void setStatus(EquipmentStatus status) {
		this.status = status;
	}

	public String getConditionDetails() {
		return conditionDetails;
	}

	public void setConditionDetails(String conditionDetails) {
		this.conditionDetails = conditionDetails;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}

	public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}

	public LocalDate getNextMaintenanceDueDate() {
		return nextMaintenanceDueDate;
	}

	public void setNextMaintenanceDueDate(LocalDate nextMaintenanceDueDate) {
		this.nextMaintenanceDueDate = nextMaintenanceDueDate;
	}

	public String getAisleNumber() {
		return aisleNumber;
	}

	public void setAisleNumber(String aisleNumber) {
		this.aisleNumber = aisleNumber;
	}

	public String getShelfNumber() {
		return shelfNumber;
	}

	public void setShelfNumber(String shelfNumber) {
		this.shelfNumber = shelfNumber;
	}
}
