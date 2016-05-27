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
import com.dto.Goods;
import com.dto.common.ErrorResult;
import com.dto.common.SingleResult;
import com.service.GoodsService;
import com.validators.GoodsValidator;

@Path("/good")
@ComponentScan("com.entities")
public class GoodsResource {

   @Autowired
   private GoodsService goodsService; 
   
   @Autowired
   private GoodsValidator goodsValidator;
   
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   public Response createGoods(@NotNull Goods goods) throws SQLException, ClassNotFoundException {
      BeanPropertyBindingResult result = new BeanPropertyBindingResult(goods, "Goods");
      ValidationUtils.invokeValidator(goodsValidator, goods, result);
      List<ObjectError> errors = result.getAllErrors();
      if(result.hasErrors()){
         String error = errors.get(0).getCode();
         return Response.status(Status.BAD_REQUEST).entity(new ErrorResult(error)).build();
      }
      else{
         Long goodId = goodsService.saveGood(goods);
         return Response.ok(new SingleResult(goodId)).build();
      }
     
   }

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<Goods> getGoods() throws ClassNotFoundException, SQLException {
      return goodsService.getGoods();
   }
}
