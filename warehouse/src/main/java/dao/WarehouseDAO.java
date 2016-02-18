package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Warehouse;

public class WarehouseDAO extends MainDAO {

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
		List<Warehouse> personList = session.createQuery("from Person").list();
		session.close();
		return personList;
	}

}
