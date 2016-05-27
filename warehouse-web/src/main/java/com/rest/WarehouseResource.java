package com.rest;

import java.sql.SQLException;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;

import com.dto.Warehouse;
import com.dto.WarehouseItem;
import com.dto.WarehouseItemDecreaseAmount;
import com.dto.common.ErrorResult;
import com.dto.common.SingleResult;
import com.service.WarehouseItemService;
import com.service.WarehouseService;
import com.validators.WarehouseValidator;

@Path("/warehouse")
@ComponentScan("com.entities")
public class WarehouseResource {
	
   @Autowired
   private WarehouseValidator warehouseValidator;
   
	@Autowired
	private WarehouseService warehouseService; 
	
	@Autowired
   private WarehouseItemService warehouseItemService; 
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createWarehouse(@NotNull Warehouse warehouse) throws SQLException, ClassNotFoundException {
	   BeanPropertyBindingResult result = new BeanPropertyBindingResult(warehouse, "Warehouse");
      ValidationUtils.invokeValidator(warehouseValidator, warehouse, result);
      List<ObjectError> errors = result.getAllErrors();
      if(result.hasErrors()){
         String error = errors.get(0).getCode();
         return Response.status(Status.BAD_REQUEST).entity(new ErrorResult(error)).build();
      }
      else{
         Long goodId = warehouseService.saveWarehouse(warehouse);
         return Response.ok(new SingleResult(goodId)).build();
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
