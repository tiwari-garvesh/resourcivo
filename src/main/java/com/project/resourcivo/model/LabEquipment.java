package com.project.resourcivo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="labEquipment")
public class LabEquipment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String equipmentName;

    private String category; // e.g., Electronics, Mechanical, Computer, Physics

    private LocalDate purchaseDate;

	private int quantityAvailable;

    private int totalQuantity;

    private String location; // Lab room number

    private String description; // Optional notes


    public Long getId() {
		return id;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LabEquipment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LabEquipment(String equipmentName, String category, LocalDate purchaseDate, int quantityAvailable,
			int totalQuantity, String location, String description) {
		super();
		this.equipmentName = equipmentName;
		this.category = category;
		this.purchaseDate = purchaseDate;
		this.quantityAvailable = quantityAvailable;
		this.totalQuantity = totalQuantity;
		this.location = location;
		this.description = description;
	}

	@Override
	public String toString() {
		return "LabEquipment [id=" + id + ", equipmentName=" + equipmentName + ", category=" + category
				+ ", purchaseDate=" + purchaseDate + ", quantityAvailable=" + quantityAvailable + ", totalQuantity="
				+ totalQuantity + ", location=" + location + ", description=" + description + "]";
	}
	
	

}
