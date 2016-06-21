package com.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.entities.GoodsEntity;

/**
 * Represents REST API data model for warehouse
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Goods extends BaseDTO {

   private Long id;

   private String name;

   private String description;
   
   public Goods() {
    //need this empty constructor for Jersey 
   }
   
   public Goods (GoodsEntity warehouseEntity){
      this.id = warehouseEntity.getId();
      this.name = warehouseEntity.getName();
      this.description = warehouseEntity.getDescription();
   }
   
   public Goods(String name, String description) {
      super();
      this.name = name;
      this.description = description;
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
   
   
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }
}
