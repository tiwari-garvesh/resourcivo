package com.project.resourcivo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "consumable_inventory")
public class InventoryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@jakarta.validation.constraints.NotBlank
	private String name;

	@jakarta.validation.constraints.NotBlank
	@jakarta.persistence.Column(unique = true)
	private String sku; // Stock Keeping Unit

	@jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
	private InventoryCategory category;

	// Inventory
	private int quantity;

	@jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
	private UnitOfMeasure unitOfMeasure;

	private int reorderLevel;

	// Details
	private String supplier;

	private java.time.LocalDate expiryDate;

	private double pricePerUnit;

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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public InventoryCategory getCategory() {
		return category;
	}

	public void setCategory(InventoryCategory category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public int getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public java.time.LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(java.time.LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public InventoryItem() {
	}

	@Override
	public String toString() {
		return "InventoryItem{" +
				"id=" + id +
				", name='" + name + '\'' +
				", sku='" + sku + '\'' +
				", category=" + category +
				", quantity=" + quantity +
				", unitOfMeasure=" + unitOfMeasure +
				", reorderLevel=" + reorderLevel +
				", supplier='" + supplier + '\'' +
				", expiryDate=" + expiryDate +
				", pricePerUnit=" + pricePerUnit +
				'}';
	}
}
