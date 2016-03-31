package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entities.BaseEntity;


public abstract class BaseDao <T extends BaseEntity> {
  
    protected SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

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

    @SuppressWarnings("unchecked")
    public List<T> list() {
       Session session = sessionFactory.openSession();
       Criteria crit = session.createCriteria(type);
       List<T> cats = crit.list();
       session.close();
       return cats;
    }
}
