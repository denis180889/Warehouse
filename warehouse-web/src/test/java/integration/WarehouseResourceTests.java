package integration;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.junit.Before;
import org.junit.Test;

import com.dto.Warehouse;
import com.dto.WarehouseItem;
import com.dto.WarehouseItemDecreaseAmount;
import com.dto.common.SingleResult;

import utils.DatabaseCleaner;

public class WarehouseResourceTests extends BaseIntegrationTest {
   
   @Before
   public void createClient() throws ClassNotFoundException, SQLException{    
      databaseCleaner = new DatabaseCleaner();
      databaseCleaner.cleanTable("warehouse");
      databaseCleaner.cleanTable("warehouse_item");
      databaseCleaner.closeConnection();
      
      Client client = ClientBuilder.newClient();
      this.target = client.target(applicationUrl);     
   }
   
   @Test
   public void createWarehouseTest(){
      Warehouse warehouse = new Warehouse("testName", "testDescription", 1, 2, 3);
      response = createPostConnection ("/warehouse", warehouse);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals(1L, id);
   }
   
   @Test
   public void getWarehousesTest(){
      Warehouse warehouse = new Warehouse("testName", "testDescription", 1, 2, 3);
      createPostConnection ("/warehouse", warehouse);
      response = createGetConnection ("/warehouse");
      List<Object> jsonResponse = response.readEntity(List.class);
      assertEquals(1, jsonResponse.size());
   }
   
   @Test
   public void addGoodToWarehouseTest(){
      WarehouseItem warehouseItem = new WarehouseItem(1, 1, 3);
      response = createPostConnection ("/warehouse/goods/add", warehouseItem);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals(1, id);
   }
   
   @Test
   public void removeGoodFromWarehouseTest(){
      WarehouseItem warehouseItem = new WarehouseItem(1, 1, 3);
      createPostConnection ("/warehouse/goods/add", warehouseItem);
      WarehouseItemDecreaseAmount warehouseDecrease = new WarehouseItemDecreaseAmount(1, 1);
      response = createPostConnection ("/warehouse/goods/remove", warehouseDecrease);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals(1, id);
   }
}
