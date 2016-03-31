package com.rest;

import java.sql.SQLException;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import com.dto.Warehouse;
import com.dto.common.SingleResult;
import com.service.WarehouseService;

@Path("/warehouse")
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
	public List<Warehouse> getWarehouses() throws ClassNotFoundException, SQLException {
		return warehouseService.getWarehouses();
	}
}
