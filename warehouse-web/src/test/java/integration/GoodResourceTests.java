package integration;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.junit.Before;
import org.junit.Test;
import com.dto.Good;
import com.dto.common.SingleResult;
import utils.DatabaseCleaner;

public class GoodResourceTests extends BaseIntegrationTest {

   @Before
   public void createClient() throws ClassNotFoundException, SQLException {
      databaseCleaner = new DatabaseCleaner();
      databaseCleaner.cleanTable("goods");
      databaseCleaner.closeConnection();

      Client client = ClientBuilder.newClient();
      this.target = client.target(applicationUrl);
   }

   @Test
   public void createGoodTest() {
      Good good = new Good("banana", "very sweet");
      response = createPostConnection("/good", good);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals(1, id);
   }

   @Test
   public void getGoodsTest() {
      Good good = new Good("banana", "very sweet");
      createPostConnection("/good", good);
      response = createGetConnection("/good");
      List<Object> jsonResponse = response.readEntity(List.class);
      assertEquals(1, jsonResponse.size());
   }

}
