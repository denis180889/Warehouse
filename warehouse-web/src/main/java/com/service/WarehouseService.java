package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.dao.WarehouseDao;
import com.dto.Warehouse;
import com.entities.WarehouseEntity;

public class WarehouseService {

	@Autowired
	private WarehouseDao warehouseDao; 
	
	public Long saveWarehouse(Warehouse wh){
		WarehouseEntity entity = new WarehouseEntity(wh.getName(), wh.getDescription(), wh.getLongitude(), wh.getLatitude(), wh.getCapacity());
		return warehouseDao.save(entity);
	}
	
	public List<WarehouseEntity> getWarehouses(){
		List<WarehouseEntity> list = warehouseDao.list();
		return list;
	}
}
