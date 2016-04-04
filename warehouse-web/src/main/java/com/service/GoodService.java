package com.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.dao.GoodDao;
import com.dto.Good;
import com.entities.GoodEntity;

public class GoodService {

   @Autowired
   private GoodDao goodDao; 
   
   @Transactional
   public Long saveGood(Good wh){
      GoodEntity entity = new GoodEntity(wh.getName(), wh.getDescription());
      return goodDao.save(entity);
   }
   
   public List<Good> getGoods(){
      List<GoodEntity> listGe = goodDao.list();
      List<Good> listG = new ArrayList<Good>();
      for(GoodEntity entity:listGe){
         listG.add(new Good(entity));
      }
      return listG;
   }
   
}
