package com.rest;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import com.entities.Warehouse;
import com.service.WarehouseService;

@Path("/items")
@ComponentScan("com.entities")
public class WarehouseResource {
	
	@Autowired
	private WarehouseService warehouseService; 
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void createWarehouse(@FormParam("name") String name, @FormParam("description") String description,
			@FormParam("longitude") float longitude, @FormParam("latitude") float latitude,
			@FormParam("capacity") int capacity) throws SQLException, ClassNotFoundException {

		warehouseService.injectWarehouse(name, description, longitude, latitude, capacity);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Warehouse> getWarehouses() throws ClassNotFoundException, SQLException {

		return warehouseService.getWarehouses();
	}
}
