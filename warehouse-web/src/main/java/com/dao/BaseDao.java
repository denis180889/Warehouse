package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.entities.BaseEntity;


public abstract class BaseDao <T extends BaseEntity> {
  
    @Autowired
    private SessionFactory sessionFactory;
    
    public Long save(T p) {
       Session session = sessionFactory.openSession();
       session.save(p);
       session.close();
       return p.getId();
    }

    @SuppressWarnings("unchecked")
    public List<T> list(T p) {
       String type = p.getClass().getName();
       Session session = sessionFactory.openSession();
       List<T> personList = session.createQuery("from "+type).list();
       session.close();
       return personList;
    }
}
