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

import com.dto.Goods;
import com.dto.Warehouse;
import com.dto.WarehouseItem;
import com.dto.WarehouseItemDecreaseAmount;
import com.dto.common.ErrorResult;
import com.dto.common.SingleResult;
import utils.DatabaseCleaner;
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
      DatabaseCleaner.cleanTable("warehouse");
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
      DatabaseCleaner.cleanTable("warehouse");
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
   public void addGoodToWarehouse(){
      DatabaseCleaner.cleanTable("warehouse_item");
      WarehouseItem warehouseItem = new WarehouseItem(1, 1, 3);
      response = post (Path.WAREHOUSE_GOODS_ADD, warehouseItem);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals(1, id);
   }
   
   @Test
   public void removeGoodFromWarehouse(){
      DatabaseCleaner.cleanTable("warehouse_item");
      WarehouseItem warehouseItem = new WarehouseItem(1, 1, 3);
      post (Path.WAREHOUSE_GOODS_ADD, warehouseItem);
      WarehouseItemDecreaseAmount warehouseDecrease = new WarehouseItemDecreaseAmount(1, 1);
      response = post (Path.WAREHOUSE_GOODS_REMOVE, warehouseDecrease);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals(1, id);
   }
   
   @Test
   public void incorrectLongtitudeWarehouse(){
      Random rn = new Random();
      int value = rn.nextInt(100000) + 181;
      
      DatabaseCleaner.cleanTable("warehouse");
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
      
      DatabaseCleaner.cleanTable("warehouse");
      Warehouse warehouse = new Warehouse("testName", "testDescription", 1, value, 3);
      response = post (Path.WAREHOUSE, warehouse);
      ErrorResult result = response.readEntity(ErrorResult.class);
      String error = result.getError();
      assertEquals(Errors.INVALID_LATITUDE.getErrorName(), error);
   }
   
   @Test
   public void wrongNameWarehouse(){
      DatabaseCleaner.cleanTable("warehouse");
      Warehouse warehouse = new Warehouse("ыы", "testDescription", 1, 2, 3);
      response = post (Path.WAREHOUSE, warehouse);
      ErrorResult result = response.readEntity(ErrorResult.class);
      String error = result.getError();
      assertEquals(Errors.INVALID_WAREHOUSE_NAME.getErrorName(), error);
   }
}
