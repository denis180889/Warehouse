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

import com.dto.Good;
import com.dto.common.SingleResult;
import com.service.GoodsService;

@Path("/good")
@ComponentScan("com.entities")
public class GoodsResource {

   @Autowired
   private GoodsService goodService; 
   
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   public SingleResult createGood(@NotNull Good good) throws SQLException, ClassNotFoundException {

      Long goodId = goodService.saveGood(good);
      return new SingleResult(goodId);
   }

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<Good> getGoods() throws ClassNotFoundException, SQLException {
      return goodService.getGoods();
   }
}
