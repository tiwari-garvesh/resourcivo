package com.project.resourcivo.dto;

import com.project.resourcivo.model.EquipmentStatus;
import com.project.resourcivo.model.LabType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class LabEquipmentCreateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Lab Type is required")
    private LabType labType;

    private String category;

    @NotNull
    private Integer totalQuantity;

    private Integer availableQuantity; // Optional, defaults to total if null

    private EquipmentStatus status;

    private String conditionDetails;

    private String modelNumber;

    private String description;

    private LocalDate lastMaintenanceDate;
    private LocalDate nextMaintenanceDueDate;

    private String aisleNumber;
    private String shelfNumber;

    // Getters and Setters
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

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
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
