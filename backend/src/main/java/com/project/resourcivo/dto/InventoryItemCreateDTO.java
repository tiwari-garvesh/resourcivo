package com.project.resourcivo.dto;

import com.project.resourcivo.model.InventoryCategory;
import com.project.resourcivo.model.UnitOfMeasure;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class InventoryItemCreateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "SKU is required")
    private String sku;

    @NotNull(message = "Category is required")
    private InventoryCategory category;

    @NotNull
    private Integer quantity;

    @NotNull
    private UnitOfMeasure unitOfMeasure;

    private Integer reorderLevel;

    private String supplier;

    private LocalDate expiryDate;

    private Double pricePerUnit;

    // Getters and Setters
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
