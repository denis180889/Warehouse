package com.dao;

import org.springframework.stereotype.Component;
import com.entities.GoodEntity;

@Component
public class GoodDao extends BaseDao<GoodEntity> {

   public GoodDao() {
      super(GoodEntity.class);
   }

}

