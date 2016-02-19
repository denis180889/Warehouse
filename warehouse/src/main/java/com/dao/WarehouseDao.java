package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.entities.Warehouse;

@Component
public class WarehouseDao extends BaseDao {

	public void save(Warehouse p) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(p);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Warehouse> list() {
		Session session = sessionFactory.openSession();
		List<Warehouse> personList = session.createQuery("from Warehouse").list();
		session.close();
		return personList;
	}

}
