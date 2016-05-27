package com.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;

import com.dao.GoodsDao;
import com.dto.Goods;
import com.entities.GoodsEntity;
import com.entities.WarehouseEntity;
import com.validators.GoodsValidator;
import com.validators.WarehouseValidator;

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
      List<Goods> listG = new ArrayList<Goods>();
      for(GoodsEntity entity:listGe){
         listG.add(new Goods(entity));
      }
      return listG;
   }
   
}
