package com.rest;

import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.connection.DatabaseConnection;
import com.entities.CreateItemResult;

@Path("/create")
public class ItemService {

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
}
