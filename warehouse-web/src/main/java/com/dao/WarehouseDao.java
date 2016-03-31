package com.dao;

import org.springframework.stereotype.Component;

import com.entities.WarehouseEntity;

@Component
public class WarehouseDao extends BaseDao<WarehouseEntity> {

   public WarehouseDao() {
      super(WarehouseEntity.class);
   }

}
