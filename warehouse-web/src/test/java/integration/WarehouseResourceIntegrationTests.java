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
      assertEquals(1L, id);
   }
   
   @Test
   public void getWarehouses(){
      DatabaseCleaner.cleanTable("warehouse");
      Warehouse warehouse = new Warehouse("testName", "testDescription", 1, 2, 3);
      post (Path.WAREHOUSE, warehouse);
      response = get (Path.WAREHOUSE);
      List<Object> jsonResponse = response.readEntity(List.class);
      assertEquals(1, jsonResponse.size());
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
}
