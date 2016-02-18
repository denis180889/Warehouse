package com.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.entities.Warehouse;
import dao.WarehouseDAO;

@Path("/items")
public class WarehouseResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void createItem(@FormParam("name") String name, @FormParam("description") String description,
			@FormParam("longitude") float longitude, @FormParam("latitude") float latitude,
			@FormParam("capacity") int capacity) throws SQLException, ClassNotFoundException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		WarehouseDAO warehouseDAO = context.getBean(WarehouseDAO.class);

		Warehouse warehouse = new Warehouse();
		warehouse.setName(name);
		warehouse.setDescription(description);
		warehouse.setLatitude(latitude);
		warehouse.setLongitude(longitude);
		warehouse.setCapacity(capacity);

		warehouseDAO.save(warehouse);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Warehouse> getItems() throws ClassNotFoundException, SQLException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		WarehouseDAO warehouseDAO = context.getBean(WarehouseDAO.class);

		List<Warehouse> list = warehouseDAO.list();

		return list;
	}
}
