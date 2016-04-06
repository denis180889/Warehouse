package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.entities.BaseEntity;


public abstract class BaseDao <T extends BaseEntity> {
  
   @Autowired
   private SessionFactory sessionFactory;

   private Class<T> type;
    
    public BaseDao(Class<T> type) {
       this.type = type;
   }
    
    public Long save(T p) {
       Session session = sessionFactory.openSession();
       session.save(p);
       session.close();
       return p.getId();
    }
    
    public Long remove(T p) {
       Session session = sessionFactory.openSession();
       session.delete(p);
       session.close();
       return p.getId();
    }

    @SuppressWarnings("unchecked")
    public List<T> list() {
       Session session = sessionFactory.openSession();
       Criteria crit = session.createCriteria(type);
       List<T> cats = crit.list();
       session.close();
       return cats;
    }
}
