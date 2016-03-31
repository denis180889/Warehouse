package com.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dao.WarehouseDao;
import com.dto.Warehouse;
import com.entities.WarehouseEntity;

public class WarehouseService {

	@Autowired
	private WarehouseDao warehouseDao; 
	
	WarehouseEntity type = null;
	
	@Transactional
	public Long saveWarehouse(Warehouse wh){
		WarehouseEntity entity = new WarehouseEntity(wh.getName(), wh.getDescription(), wh.getLongitude(), wh.getLatitude(), wh.getCapacity());
		return warehouseDao.save(entity);
	}
	
	public List<Warehouse> getWarehouses(){
		List<WarehouseEntity> listWE = warehouseDao.list();
		List<Warehouse> listW = new ArrayList<Warehouse>();
		for(WarehouseEntity entity:listWE){
			listW.add(new Warehouse(entity));
		}
		return listW;
	}
}
