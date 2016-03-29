package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entities.WarehouseEntity;

@Component
public class WarehouseDao extends BaseDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Long save(WarehouseEntity p) {
		Session session = sessionFactory.openSession();
		session.save(p);
		session.close();
		return p.getId();
	}

	@SuppressWarnings("unchecked")
	public List<WarehouseEntity> list() {
		Session session = sessionFactory.openSession();
		List<WarehouseEntity> personList = session.createQuery("from WarehouseEntity").list();
		session.close();
		return personList;
	}

}
