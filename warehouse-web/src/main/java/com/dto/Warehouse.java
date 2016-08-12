package com.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.entities.WarehouseEntity;

/**
 * Represents REST API data model for warehouse
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Warehouse extends BaseDTO {

   private Long id;

   private String name;

   private String description;

   private float longitude;

   private float latitude;

   private int capacity;

   public Warehouse() {
      //need this empty constructor for Jersey 
   }
   
   public Warehouse (WarehouseEntity warehouseEntity){
      this.id = warehouseEntity.getId();
	   this.name = warehouseEntity.getName();
	   this.description = warehouseEntity.getDescription();
	   this.longitude = warehouseEntity.getLongitude();
	   this.latitude = warehouseEntity.getLatitude();
	   this.capacity = warehouseEntity.getCapacity();
   }
   
   public Warehouse(String name, String description, float longitude, float latitude, int capacity) {
      super();
      this.name = name;
      this.description = description;
      this.longitude = longitude;
      this.latitude = latitude;
      this.capacity = capacity;
   }
   
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public float getLongitude() {
      return longitude;
   }

   public void setLongitude(float longitude) {
      this.longitude = longitude;
   }

   public float getLatitude() {
      return latitude;
   }

   public void setLatitude(float latitude) {
      this.latitude = latitude;
   }

   public int getCapacity() {
      return capacity;
   }

   public void setCapacity(int capacity) {
      this.capacity = capacity;
   }
   
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

}
