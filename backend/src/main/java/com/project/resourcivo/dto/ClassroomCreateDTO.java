package com.project.resourcivo.dto;

public class ClassroomCreateDTO {
    private String name;
    private Integer floor;
    private Integer capacity;
    private Boolean hasProjector;
    private Boolean hasSmartboard;
    private Boolean isAvailable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(Boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public Boolean getHasSmartboard() {
        return hasSmartboard;
    }

    public void setHasSmartboard(Boolean hasSmartboard) {
        this.hasSmartboard = hasSmartboard;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
