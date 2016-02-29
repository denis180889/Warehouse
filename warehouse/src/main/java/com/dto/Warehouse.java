package com.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents REST API data model for warehouse
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Warehouse {

   private String name;

   private String description;

   private float longitude;

   private float latitude;

   private int capacity;

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


}
