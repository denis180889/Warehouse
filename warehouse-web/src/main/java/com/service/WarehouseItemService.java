package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dao.WarehouseItemDao;
import com.dto.WarehouseItem;
import com.entities.WarehouseItemEntity;

public class WarehouseItemService {

   @Autowired
   private WarehouseItemDao warehouseItemDao; 
   
   @Transactional
   public Long addGoodToWarehouse(WarehouseItem warehouseItem){
      WarehouseItemEntity entity = new WarehouseItemEntity(warehouseItem.getWarehouseId(), warehouseItem.getGoodId(), warehouseItem.getAmount());
      return warehouseItemDao.save(entity);
   }
   
   @Transactional
   public Long increaseGoodFromWarehouse(int warehouse_id, int good_id , int amount){
      return warehouseItemDao.increaseAmount(warehouse_id, good_id, amount);
   }
   
   @Transactional
   public Long removeGoodFromWarehouse(int warehouse_id, int good_id , int amount){
      return warehouseItemDao.decreaseAmount(warehouse_id, good_id, amount);
   }
 
   @Transactional
   public boolean checkIfWarehouseExists(int warehouse_id){
      return warehouseItemDao.checkIfWarehouseExists(warehouse_id);
   }
   
   @Transactional
   public boolean checkIfGoodExists(int good_id){
      return warehouseItemDao.checkIfGoodExists(good_id);
   }
   
   @Transactional
   public boolean checkIfGoodExistsOnWarehouse(int warehouse_id, int good_id){
      return warehouseItemDao.checkIfGoodExistsOnWarehouse(warehouse_id, good_id);
   }
}
