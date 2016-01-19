package com.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.entities.Item;

@Path("/food")
public class ItemService {

	@POST
	// @Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Item getMsg(@QueryParam("msg") String msg, @QueryParam("name") String name) {
		Item item = new Item();
		item.setMsg(msg);
		item.setName(name);
		return item;
	}
}
