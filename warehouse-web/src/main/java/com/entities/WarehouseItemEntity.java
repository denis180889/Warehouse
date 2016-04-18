package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "warehouse_item")
public class WarehouseItemEntity extends BaseEntity {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(name = "warehouse_id")
   private int warehouse_id;

   @Column(name = "good_id")
   private int good_id;
   
   @Column(name = "amount")
   private int amount;

   public WarehouseItemEntity() {
   }

   public WarehouseItemEntity(int warehouse_id, int good_id, int amount) {
      this.warehouse_id = warehouse_id;
      this.good_id = good_id;
      this.amount = amount;
   }
   
   public Long getId() {
      return id;
   }

   public int getWarehouseId() {
      return warehouse_id;
   }
   
   public int getGoodId() {
      return good_id;
   }

   public int getAmount() {
      return amount;
   }

   public void setGood_id(int good_id) {
      this.good_id = good_id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setWarehouse_id(int warehouse_id) {
      this.warehouse_id = warehouse_id;
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }
}
