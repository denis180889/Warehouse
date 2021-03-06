package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goods")
public class GoodsEntity extends BaseEntity {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private String description;

   public GoodsEntity() {
      //need this empty constructor for Jersey 
   }
   
   public GoodsEntity(String name, String description) {
      this.name = name;
      this.description = description;
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

}
