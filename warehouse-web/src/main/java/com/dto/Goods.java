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

   private String name;

   private String description;
   
   public Goods() {
   }
   
   public Goods (GoodsEntity warehouseEntity){
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
}
