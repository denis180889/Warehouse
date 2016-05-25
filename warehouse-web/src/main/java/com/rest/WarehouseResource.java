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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dto.Warehouse;
import com.dto.WarehouseItem;
import com.dto.WarehouseItemDecreaseAmount;
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
	public ResponseEntity<String> createWarehouse(@NotNull Warehouse warehouse) throws SQLException, ClassNotFoundException {
	   String body = warehouseService.saveWarehouse(warehouse);
	   if(body.contains("ERROR_CODE")){
	      return new ResponseEntity<String>(body.toString(),HttpStatus.BAD_REQUEST);
	   }
	   else{
	      return new ResponseEntity<String>(body.toString(),HttpStatus.OK);
	   }
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Warehouse> getWarehouses() throws ClassNotFoundException, SQLException {
		return warehouseService.getWarehouses();
	}
	
	@POST
	@Path("/goods/add")
   @Produces(MediaType.APPLICATION_JSON)
   public SingleResult addGoodToWarehouse(@NotNull WarehouseItem warehouseItem) throws SQLException, ClassNotFoundException {

      Long warehouseId = warehouseItemService.addGoodToWarehouse(warehouseItem);
      return new SingleResult(warehouseId);
   }
	
	@POST
   @Path("/goods/remove")
   @Produces(MediaType.APPLICATION_JSON)
   public SingleResult removeGoodFromWarehouse(@NotNull WarehouseItemDecreaseAmount wIDAmount) throws SQLException, ClassNotFoundException {
	   Long warehouseItemId = warehouseItemService.removeGoodFromWarehouse(wIDAmount.getId(), wIDAmount.getAmount());
      return new SingleResult(warehouseItemId);
   }

}
