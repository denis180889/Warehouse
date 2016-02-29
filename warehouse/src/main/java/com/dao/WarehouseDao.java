package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entities.WarehouseEntity;

@Component
public class WarehouseDao extends BaseDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Long save(WarehouseEntity p) {
	   
	   // TODO: refactor to use declarative transaction configuration
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(p);
		tx.commit();
		session.close();
		
		return p.getId();
	}

	@SuppressWarnings("unchecked")
	public List<WarehouseEntity> list() {
		Session session = sessionFactory.openSession();
		List<WarehouseEntity> personList = session.createQuery("from Warehouse").list();
		session.close();
		return personList;
	}

}
