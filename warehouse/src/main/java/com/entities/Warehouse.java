package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Item")
public class Warehouse {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	public String name;
	public String description;
	public float longitude;
	public float latitude;
	public int capacity;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Warehouse() {
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", description=" + description + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", capacity=" + capacity;
	}
}
