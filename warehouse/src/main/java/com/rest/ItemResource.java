package com.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.connection.DatabaseConnection;
import com.entities.CreateItemResult;
import com.entities.Warehouse;


@Path("/items")
public class ItemResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public CreateItemResult createItem(@FormParam("name") String name, 
							@FormParam("description") String description, 
							@FormParam("longitude") float longitude,  
							@FormParam("latitude") float latitude,  
							@FormParam("capacity") int capacity ) throws SQLException, ClassNotFoundException {
		
		DatabaseConnection dbC = new DatabaseConnection();
		int id = dbC.insertRowInItemsTable(name, description, longitude, latitude, capacity);
		CreateItemResult result = new CreateItemResult();
		result.setId(id);
		return result;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Warehouse> getItems() throws ClassNotFoundException, SQLException{
		List<Warehouse> allItems = new ArrayList<Warehouse>();
		DatabaseConnection dbC = new DatabaseConnection();
		allItems = dbC.getAllItems();
		
		return allItems;
		
		
	}
}
