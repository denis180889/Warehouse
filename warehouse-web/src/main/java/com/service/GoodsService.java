package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GoodsDao;
import com.dto.Goods;
import com.entities.GoodsEntity;

public class GoodsService {
   
   @Autowired
   private GoodsDao goodsDao; 
   
   @Transactional
   public Long saveGood(Goods wh){
     GoodsEntity entity = new GoodsEntity(wh.getName(), wh.getDescription());
     return goodsDao.save(entity);
   }
   
   public List<Goods> getGoods(){
      List<GoodsEntity> listGe = goodsDao.list();
      List<Goods> listG = new ArrayList<>();
      for(GoodsEntity entity:listGe){
         listG.add(new Goods(entity));
      }
      return listG;
   }
   
}
