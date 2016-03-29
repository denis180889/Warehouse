package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class WarehouseEntity {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private String description;

   private float longitude;

   private float latitude;

   private int capacity;

   public WarehouseEntity() {
   }

   public WarehouseEntity(String name, String description, float longitude, float latitude, int capacity) {
      this.name = name;
      this.description = description;
      this.longitude = longitude;
      this.latitude = latitude;
      this.capacity = capacity;
   }


   public Long getId() {
      return id;
   }


   public String getName() {
      return name;
   }


   public String getDescription() {
      return description;
   }


   public float getLongitude() {
      return longitude;
   }


   public float getLatitude() {
      return latitude;
   }


   public int getCapacity() {
      return capacity;
   }


}