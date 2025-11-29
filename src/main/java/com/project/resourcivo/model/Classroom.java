package com.project.resourcivo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "classroom")
public class Classroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "classroom_id")
	private Long id;

	private String name;

	private Integer floor;

	private Integer capacity;

	private Boolean hasProjector;

	private Boolean hasSmartboard;

	private Boolean isAvailable;

	public Long getId() {
		return id;
	}

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

	public Classroom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Classroom(String name, Integer floor, Integer capacity, Boolean hasProjector, Boolean hasSmartboard,
			Boolean isAvailable) {
		super();
		this.name = name;
		this.floor = floor;
		this.capacity = capacity;
		this.hasProjector = hasProjector;
		this.hasSmartboard = hasSmartboard;
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Classroom [id=" + id + ", name=" + name + ", floor=" + floor + ", capacity=" + capacity
				+ ", hasProjector=" + hasProjector + ", hasSmartboard=" + hasSmartboard + ", isAvailable=" + isAvailable
				+ "]";
	}

}
