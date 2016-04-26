package com.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.dao.GoodDao;
import com.dto.Good;
import com.entities.GoodsEntity;

public class GoodsService {

   @Autowired
   private GoodDao goodDao; 
   
   @Transactional
   public Long saveGood(Good wh){
      GoodsEntity entity = new GoodsEntity(wh.getName(), wh.getDescription());
      return goodDao.save(entity);
   }
   
   public List<Good> getGoods(){
      List<GoodsEntity> listGe = goodDao.list();
      List<Good> listG = new ArrayList<Good>();
      for(GoodsEntity entity:listGe){
         listG.add(new Good(entity));
      }
      return listG;
   }
   
}
