package com.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents REST API data model for warehouse
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WarehouseItem {

   private int warehouse_id;

   private int good_id;
   
   private int amount;

   public WarehouseItem() {
   }
   
   public WarehouseItem(int warehouse_id, int good_id, int amount) {
      this.warehouse_id = warehouse_id;
      this.good_id = good_id;
      this.amount = amount;
   }
   
   public int getWarehouseId() {
      return warehouse_id;
   }

   public void setWarehouseId(int warehouse_id) {
      this.warehouse_id = warehouse_id;
   }

   public int getGoodId() {
      return good_id;
   }

   public void setGoodId(int good_id) {
      this.good_id = good_id;
   }

   public int getAmount() {
      return amount;
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }
}
