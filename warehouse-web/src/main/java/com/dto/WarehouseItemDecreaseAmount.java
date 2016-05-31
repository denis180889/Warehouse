package com.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WarehouseItemDecreaseAmount extends BaseDTO {

   private long id;

   private int amount;
   
   public WarehouseItemDecreaseAmount() {
   }

   public WarehouseItemDecreaseAmount(long id, int amount) {
      super();
      this.id = id;
      this.amount = amount;
   }
   
   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public int getAmount() {
      return amount;
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }
   
   
}
