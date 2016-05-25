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
   private GoodsValidator goodsValidator;
   
   @Autowired
   private GoodsDao goodsDao; 
   
   @Transactional
   public String saveGood(Goods wh){
      BeanPropertyBindingResult result = new BeanPropertyBindingResult(wh, "Goods");
      ValidationUtils.invokeValidator(goodsValidator, wh, result);
      List<ObjectError> errors = result.getAllErrors();
      if(!result.hasErrors()){
         GoodsEntity entity = new GoodsEntity(wh.getName(), wh.getDescription());
         return goodsDao.save(entity).toString();
      }
      else{
         return "ERROR_CODE : " + errors.get(0).getCode();
      }
    
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
