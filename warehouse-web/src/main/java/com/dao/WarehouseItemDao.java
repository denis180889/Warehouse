package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.entities.WarehouseItemEntity;

@Component
public class WarehouseItemDao extends BaseDao<WarehouseItemEntity> {

   public WarehouseItemDao() {
      super(WarehouseItemEntity.class);
   }

   public Long increaseAmount(int warehouse_id, int good_id, int amount) {
      Session session = sessionFactory.openSession();
      Criteria cr = session.createCriteria(WarehouseItemEntity.class);
      cr.add(Restrictions.eq("warehouse_id", warehouse_id));
      cr.add(Restrictions.eq("good_id", good_id));
      WarehouseItemEntity result = (WarehouseItemEntity)cr.uniqueResult();
 
     
      int newAmount = result.getAmount() + amount;
      result.setAmount(newAmount);
      session.update(result);
      Long idR = result.getId();
      session.flush();
      return idR;
   }
   
   public Long decreaseAmount(int warehouse_id, int good_id, int amount) {
      Session session = sessionFactory.openSession();
      Criteria cr = session.createCriteria(WarehouseItemEntity.class);
      cr.add(Restrictions.eq("warehouse_id", warehouse_id));
      cr.add(Restrictions.eq("good_id", good_id));
      WarehouseItemEntity result = (WarehouseItemEntity)cr.uniqueResult();
 
     
      int newAmount = result.getAmount() - amount;
      result.setAmount(newAmount);
      session.update(result);
      Long idR = result.getId();
      session.flush();
      return idR;
   }
 
   public boolean checkIfWarehouseExists(int warehouse_id){
      Session session = sessionFactory.openSession();
      Criteria cr = session.createCriteria(WarehouseItemEntity.class);
      cr.add(Restrictions.eq("warehouse_id", warehouse_id));
      List result = cr.list();
      if(result.isEmpty()){
         return false;
      }
      else{
         return true;
      }
   }
   
   public boolean checkIfGoodExists(int good_id){
      Session session = sessionFactory.openSession();
      Criteria cr = session.createCriteria(WarehouseItemEntity.class);
      cr.add(Restrictions.eq("good_id", good_id));
      List result = cr.list();
      if(result.isEmpty()){
         return false;
      }
      else{
         return true;
      }
   }
   
   public boolean checkIfGoodExistsOnWarehouse(int warehouse_id, int good_id){
      Session session = sessionFactory.openSession();
      Criteria cr = session.createCriteria(WarehouseItemEntity.class);
      cr.add(Restrictions.eq("warehouse_id", warehouse_id));
      cr.add(Restrictions.eq("good_id", good_id));
      List result = cr.list();
      if(result.isEmpty()){
         return false;
      }
      else{
         return true;
      }
   }
   
   
}