package com.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.WarehouseDao;
import com.entities.Warehouse;

@Path("/items")
@ComponentScan("com.entities")
public class WarehouseResource {
	
	@Autowired
	private WarehouseDao warehouseDao; 
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void createItem(@FormParam("name") String name, @FormParam("description") String description,
			@FormParam("longitude") float longitude, @FormParam("latitude") float latitude,
			@FormParam("capacity") int capacity) throws SQLException, ClassNotFoundException {

		Warehouse warehouse = new Warehouse();
		warehouse.setName(name);
		warehouse.setDescription(description);
		warehouse.setLatitude(latitude);
		warehouse.setLongitude(longitude);
		warehouse.setCapacity(capacity);

		warehouseDao.save(warehouse);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Warehouse> getItems() throws ClassNotFoundException, SQLException {

		List<Warehouse> list = warehouseDao.list();

		return list;
	}
}
