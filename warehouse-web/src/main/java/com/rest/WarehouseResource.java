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
import com.dto.WarehouseItem;
import com.dto.common.SingleResult;
import com.service.WarehouseItemService;
import com.service.WarehouseService;

@Path("/warehouse")
@ComponentScan("com.entities")
public class WarehouseResource {
	
	@Autowired
	private WarehouseService warehouseService; 
	
	@Autowired
   private WarehouseItemService warehouseItemService; 
	
	
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
	
	@POST
	@Path("/addgood")
   @Produces(MediaType.APPLICATION_JSON)
   public SingleResult addGoodToWarehouse(@NotNull WarehouseItem warehouseItem) throws SQLException, ClassNotFoundException {

      Long warehouseId = warehouseItemService.addGoodToWarehouse(warehouseItem);
      return new SingleResult(warehouseId);
   }
	
	@POST
   @Path("/removegood")
   @Produces(MediaType.APPLICATION_JSON)
   public SingleResult removeGoodToWarehouse(@NotNull WarehouseItem warehouseItem) throws SQLException, ClassNotFoundException {
      Long warehouseId = warehouseItemService.removeGoodFromWarehouse(warehouseItem);
      return new SingleResult(warehouseId);
   }
}
