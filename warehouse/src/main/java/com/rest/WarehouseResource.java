package com.rest;

import java.sql.SQLException;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import com.dto.Warehouse;
import com.dto.common.SingleResult;
import com.entities.WarehouseEntity;
import com.service.WarehouseService;

@Path("/items")
@ComponentScan("com.entities")
public class WarehouseResource {
	
	@Autowired
	private WarehouseService warehouseService; 
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public SingleResult createWarehouse(@NotNull Warehouse warehouse) throws SQLException, ClassNotFoundException {

		Long warehouseId = warehouseService.saveWarehouse(warehouse);
		return new SingleResult(warehouseId);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<WarehouseEntity> getWarehouses() throws ClassNotFoundException, SQLException {

	   // TODO: refactor to return list of Warehouse, not WarehouseEntity
		return warehouseService.getWarehouses();
	}
}
