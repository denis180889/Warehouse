package com.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import com.entities.WarehouseItemEntity;

@Component
public class WarehouseItemDao extends BaseDao<WarehouseItemEntity> {

   public WarehouseItemDao() {
      super(WarehouseItemEntity.class);
   }

   public Long decreaseAmount(long id , int amount) {
      Session session = sessionFactory.openSession();
      WarehouseItemEntity wItemEntity = (WarehouseItemEntity) session.get(WarehouseItemEntity.class, id );
      int newAmount = wItemEntity.getAmount() - amount;
      wItemEntity.setAmount(newAmount);
      session.update(wItemEntity);
      Long idR = wItemEntity.getId();
      session.flush();
      return idR;
   }
 
}