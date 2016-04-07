package com.dao;

import org.springframework.stereotype.Component;
import com.entities.GoodsEntity;

@Component
public class GoodDao extends BaseDao<GoodsEntity> {

   public GoodDao() {
      super(GoodsEntity.class);
   }

}

