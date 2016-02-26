package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.dao.WarehouseDao;
import com.entities.Warehouse;

public class WarehouseService {

	@Autowired
	private WarehouseDao warehouseDao; 
	
	public void injectWarehouse( String name, String description, float longitude,  float latitude, int capacity){
		Warehouse warehouse = new Warehouse();
		warehouse.setName(name);
		warehouse.setDescription(description);
		warehouse.setLatitude(latitude);
		warehouse.setLongitude(longitude);
		warehouse.setCapacity(capacity);
		warehouseDao.save(warehouse);
	}
	
	public List<Warehouse> getWarehouses(){
		List<Warehouse> list = warehouseDao.list();
		return list;
	}
}
