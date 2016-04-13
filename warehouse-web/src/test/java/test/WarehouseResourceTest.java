package test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import com.dto.BaseDTO;
import com.dto.Warehouse;
import com.dto.common.SingleResult;

import utils.DatabaseCleaner;

import static junit.framework.Assert.*;

import java.sql.SQLException;
import java.util.List;

public class WarehouseResourceTest {
   DatabaseCleaner databaseCleaner;
   Response response;
   WebTarget target;
   String applicationUrl = "http://localhost:8080/warehouse-web/rest";
   
   public Response createPostConnection(String path, BaseDTO dto) {
      return target.path(path).request(MediaType.APPLICATION_JSON).post(Entity.json(dto));
   }
   
   public Response createGetConnection(String path) {
      return target.path(path).request(MediaType.APPLICATION_JSON).get();
   }
   
   @Before
   public void createClient() throws ClassNotFoundException, SQLException{    
      databaseCleaner = new DatabaseCleaner();
      databaseCleaner.cleanTable("warehouse");
      
      Client client = ClientBuilder.newClient();
      this.target = client.target(applicationUrl);     
   }
   
   @Test
   public void createWarehouseTest(){
      Warehouse warehouse = new Warehouse("testName", "testDescription", 1, 2, 3);
      response = createPostConnection ("/warehouse", warehouse);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals(1, id);
   }
   
   @Test
   public void getWarehousesTest(){
      Warehouse warehouse = new Warehouse("testName", "testDescription", 1, 2, 3);
      createPostConnection ("/warehouse", warehouse);
      response = createGetConnection ("/warehouse");
      List<Object> jsonResponse = response.readEntity(List.class);
      assertEquals(1, jsonResponse.size());
   }
}
