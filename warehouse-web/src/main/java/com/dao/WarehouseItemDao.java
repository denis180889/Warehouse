package com.dao;

import org.springframework.stereotype.Component;
import com.entities.WarehouseItemEntity;

@Component
public class WarehouseItemDao extends BaseDao<WarehouseItemEntity> {

   public WarehouseItemDao() {
      super(WarehouseItemEntity.class);
   }

}