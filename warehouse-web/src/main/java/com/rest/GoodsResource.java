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

import com.dto.Goods;
import com.dto.common.SingleResult;
import com.service.GoodsService;

@Path("/good")
@ComponentScan("com.entities")
public class GoodsResource {

   @Autowired
   private GoodsService goodsService; 
   
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   public ResponseEntity<String> createGoods(@NotNull Goods goods) throws SQLException, ClassNotFoundException {
      String body = goodsService.saveGood(goods);
      if(body.contains("ERROR_CODE")){
         return new ResponseEntity<String>(body.toString(),HttpStatus.BAD_REQUEST);
      }
      else{
         return new ResponseEntity<String>(body.toString(),HttpStatus.OK);
      }
     
   }

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<Goods> getGoods() throws ClassNotFoundException, SQLException {
      return goodsService.getGoods();
   }
}
