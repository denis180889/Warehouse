package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.dao.WarehouseItemDao;
import com.dto.WarehouseItem;
import com.entities.WarehouseItemEntity;

public class WarehouseItemService {

   @Autowired
   private WarehouseItemDao warehouseItemDao; 
   
   public void setWarehouseItemDao(WarehouseItemDao warehouseItemDao) {
      this.warehouseItemDao = warehouseItemDao;
   }

   @Transactional
   public Long addGoodToWarehouse(WarehouseItem warehouseItem){
      WarehouseItemEntity entity = new WarehouseItemEntity(warehouseItem.getWarehouseId(), warehouseItem.getGoodId(), warehouseItem.getAmount());
      return warehouseItemDao.save(entity);
   }
   
   @Transactional
   public Long removeGoodFromWarehouse(long id , int amount){
      return warehouseItemDao.decreaseAmount(id, amount);
   }
}
