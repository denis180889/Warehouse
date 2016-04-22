package integration;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.junit.Before;
import org.junit.Test;

import com.dto.Good;
import com.dto.common.SingleResult;

import utils.DatabaseCleaner;
import utils.Path;

public class GoodResourceIntegrationTests extends BaseIntegrationTest {

   @Before
   public void createClient() throws ClassNotFoundException, SQLException {
      Client client = ClientBuilder.newClient();
      this.target = client.target(applicationUrl);
   }

   @Test
   public void createGood() {
      DatabaseCleaner.cleanTable("goods");
      Good good = new Good("banana", "very sweet");
      response = post(Path.GOOD, good);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals(1, id);
   }

   @Test
   public void getGoods() {
      DatabaseCleaner.cleanTable("goods");
      Good good = new Good("banana", "very sweet");
      post(Path.GOOD, good);
      response = get(Path.GOOD);
      List<Object> jsonResponse = response.readEntity(List.class);
      assertEquals(1, jsonResponse.size());
   }

}
