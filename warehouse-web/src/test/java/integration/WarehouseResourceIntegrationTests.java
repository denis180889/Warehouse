package integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import org.junit.Before;
import org.junit.Test;

import com.dto.Warehouse;
import com.dto.WarehouseItem;
import com.dto.common.ErrorResult;
import com.dto.common.SingleResult;

import utils.DatabaseUtil;
import utils.Errors;
import utils.Path;

public class WarehouseResourceIntegrationTests extends BaseIntegrationTest {
   
   @Before
   public void createClient() throws ClassNotFoundException, SQLException{    
      Client client = ClientBuilder.newClient();
      this.target = client.target(applicationUrl);     
   }
   
   @Test
   public void createWarehouse(){
      DatabaseUtil.cleanTable("warehouse");
      Warehouse warehouse = new Warehouse("testName", "testDescription", 1, 2, 3);
      response = post (Path.WAREHOUSE, warehouse);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals("Id in response was wrong", 1, id);
      assertEquals("Response was wrong",200 , response.getStatus());
      List<Warehouse> existingWarehouses = get(Path.WAREHOUSE).get(new GenericType<List<Warehouse>>() {});
      assertEquals("Warehouse size was incorrect in database", 1, existingWarehouses.size());
      assertEquals("Warehouse name was incorrect in database", "testName", existingWarehouses.get(0).getName());
      assertEquals("Warehouse description was incorrect in database", "testDescription", existingWarehouses.get(0).getDescription());
      assertEquals("Warehouse latitude was incorrect in database", 2, existingWarehouses.get(0).getLatitude(), 0);
      assertEquals("Warehouse latitude was incorrect in database", 1, existingWarehouses.get(0).getLongitude(), 0);
      assertEquals("Warehouse capacity was incorrect in database", 3, existingWarehouses.get(0).getCapacity(), 0);
   }
   
   @Test
   public void getWarehouses(){
      DatabaseUtil.cleanTable("warehouse");
      Warehouse warehouse1 = new Warehouse("testName", "testDescription", 1, 2, 3);
      Warehouse warehouse2 = new Warehouse("testName", "testDescription", 1, 2, 3);
      post(Path.WAREHOUSE, warehouse1);
      post(Path.WAREHOUSE, warehouse2);
      List<Warehouse> existingWarehouses = get(Path.WAREHOUSE).get(new GenericType<List<Warehouse>>() {});
      assertEquals("Amount of goods was wrong", 2, existingWarehouses.size());
      assertTrue("Warehouse1 id was wrong", existingWarehouses.get(0).getId().equals(1L));
      assertTrue("Warehouse2 id was wrong", existingWarehouses.get(1).getId().equals(2L));
   }
   
   @Test
   public void increaseGoodsToWarehouse() throws SQLException{
      DatabaseUtil.cleanTable("warehouse_item");
      //preconditions
      WarehouseItem warehouseItem1 = new WarehouseItem(1, 7, 3);
      WarehouseItem warehouseItem2 = new WarehouseItem(2, 7, 3);
      post(Path.WAREHOUSE_GOODS_ADD, warehouseItem1);
      post(Path.WAREHOUSE_GOODS_ADD, warehouseItem2);
      
      WarehouseItem amountForChange = new WarehouseItem(2, 7, 4);
      response = post(Path.WAREHOUSE_GOODS_INCREASE, amountForChange);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals("Id in response was wrong", 2, id);
      assertEquals("Response was wrong",200 , response.getStatus());
      int rs = DatabaseUtil.getValueInTheRowById("warehouse_item", 2, "amount");
      assertEquals("Amount was not increased", 7 , rs); 
   }
   
   @Test
   public void removeGoodsFromWarehouse() throws SQLException{
      DatabaseUtil.cleanTable("warehouse_item");
      //preconditions
      WarehouseItem warehouseItem1 = new WarehouseItem(1, 7, 3);
      WarehouseItem warehouseItem2 = new WarehouseItem(2, 7, 3);
      post(Path.WAREHOUSE_GOODS_ADD, warehouseItem1);
      post(Path.WAREHOUSE_GOODS_ADD, warehouseItem2);
      
      WarehouseItem amountForChange = new WarehouseItem(2, 7, 2);
      response = post(Path.WAREHOUSE_GOODS_REMOVE, amountForChange);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals("Id in response was wrong", 2, id);
      assertEquals("Response was wrong",200 , response.getStatus());
      int rs = DatabaseUtil.getValueInTheRowById("warehouse_item", 2, "amount");
      assertEquals("Amount was not removed", 1 , rs); 
   }
   
   @Test
   public void increaseGoodsToNonExistingWarehouse() throws SQLException{
      DatabaseUtil.cleanTable("warehouse_item");
      //preconditions
      WarehouseItem warehouseItem1 = new WarehouseItem(1, 7, 3);
      post(Path.WAREHOUSE_GOODS_ADD, warehouseItem1);
      
      WarehouseItem amountForChange = new WarehouseItem(2, 7, 4);
      response = post(Path.WAREHOUSE_GOODS_INCREASE, amountForChange);
      ErrorResult result = response.readEntity(ErrorResult.class);
      String error = result.getError();
      assertEquals(Errors.NON_EXISTING_WAREHOUSE_ID.getErrorName(), error);
   }
   
   @Test
   public void increaseNonExistingGoodsToWarehouse() throws SQLException{
      DatabaseUtil.cleanTable("warehouse_item");
      //preconditions
      WarehouseItem warehouseItem1 = new WarehouseItem(1, 7, 3);
      post(Path.WAREHOUSE_GOODS_ADD, warehouseItem1);
      
      WarehouseItem amountForChange = new WarehouseItem(1, 6, 4);
      response = post(Path.WAREHOUSE_GOODS_INCREASE, amountForChange);
      ErrorResult result = response.readEntity(ErrorResult.class);
      String error = result.getError();
      assertEquals(Errors.NON_EXISTING_GOOD_ID.getErrorName(), error);
   }
   
   @Test
   public void increaseExistingGoodsOnAnotherWarehouse() throws SQLException{
      DatabaseUtil.cleanTable("warehouse_item");
      //preconditions
      WarehouseItem warehouseItem1 = new WarehouseItem(1, 7, 3);
      WarehouseItem warehouseItem2 = new WarehouseItem(2, 6, 3);
      post(Path.WAREHOUSE_GOODS_ADD, warehouseItem1);
      post(Path.WAREHOUSE_GOODS_ADD, warehouseItem2);
      
      WarehouseItem amountForChange = new WarehouseItem(1, 6, 4);
      response = post(Path.WAREHOUSE_GOODS_INCREASE, amountForChange);
      ErrorResult result = response.readEntity(ErrorResult.class);
      String error = result.getError();
      assertEquals(Errors.NON_EXISTING_GOOD_ON_WAREHOUSE.getErrorName(), error);
   }
   
   @Test
   public void incorrectLongtitudeWarehouse(){
      Random rn = new Random();
      int value = rn.nextInt(100000) + 181;
      
      DatabaseUtil.cleanTable("warehouse");
      Warehouse warehouse = new Warehouse("testName", "testDescription", value, 2, 3);
      response = post (Path.WAREHOUSE, warehouse);
      ErrorResult result = response.readEntity(ErrorResult.class);
      String error = result.getError();
      assertEquals(Errors.INVALID_LONGTITUDE.getErrorName(), error);
   }
   
   @Test
   public void incorrectLatitudeWarehouse(){
      Random rn = new Random();
      int value = rn.nextInt(100000) + 91;
      
      DatabaseUtil.cleanTable("warehouse");
      Warehouse warehouse = new Warehouse("testName", "testDescription", 1, value, 3);
      response = post (Path.WAREHOUSE, warehouse);
      ErrorResult result = response.readEntity(ErrorResult.class);
      String error = result.getError();
      assertEquals(Errors.INVALID_LATITUDE.getErrorName(), error);
   }
   
   @Test
   public void wrongNameWarehouse(){
      DatabaseUtil.cleanTable("warehouse");
      Warehouse warehouse = new Warehouse("ыы", "testDescription", 1, 2, 3);
      response = post (Path.WAREHOUSE, warehouse);
      ErrorResult result = response.readEntity(ErrorResult.class);
      String error = result.getError();
      assertEquals(Errors.INVALID_WAREHOUSE_NAME.getErrorName(), error);
   }
}
