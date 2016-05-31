package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;

import com.dao.WarehouseDao;
import com.dto.Warehouse;
import com.entities.WarehouseEntity;
import com.validators.WarehouseValidator;

public class WarehouseService {

   @Autowired
   private WarehouseValidator warehouseValidator;
   
	@Autowired
	private WarehouseDao warehouseDao; 

   @Transactional
	public Long saveWarehouse(Warehouse wh){
      BeanPropertyBindingResult result = new BeanPropertyBindingResult(wh, "Warehouse");
      ValidationUtils.invokeValidator(warehouseValidator, wh, result);
      List<ObjectError> errors = result.getAllErrors();
      
      
      
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
