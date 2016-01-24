package com.entities;

import javax.xml.bind.annotation.*;


//@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
	 String name;
	 String description;
	 float longitude;
	 float latitude;
	 int capacity;
	
	
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

	public Item() {
	}
}
