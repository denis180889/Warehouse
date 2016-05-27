package com.dao;

import org.springframework.stereotype.Component;

import com.entities.GoodsEntity;

@Component
public class GoodsDao extends BaseDao<GoodsEntity> {

   public GoodsDao() {
      super(GoodsEntity.class);
   }

}

