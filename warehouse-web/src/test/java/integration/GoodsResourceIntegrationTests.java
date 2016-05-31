package integration;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.junit.Before;
import org.junit.Test;

import com.dto.Goods;
import com.dto.common.ErrorResult;
import com.dto.common.SingleResult;

import utils.DatabaseCleaner;
import utils.Errors;
import utils.Path;

public class GoodsResourceIntegrationTests extends BaseIntegrationTest {

   @Before
   public void createClient() throws ClassNotFoundException, SQLException {
      Client client = ClientBuilder.newClient();
      this.target = client.target(applicationUrl);
   }

   @Test
   public void createGood() {
      DatabaseCleaner.cleanTable("goods");
      Goods goods = new Goods("banana", "very sweet");
      response = post(Path.GOOD, goods);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals(1, id);
   }

   @Test
   public void getGoods() {
      DatabaseCleaner.cleanTable("goods");
      Goods goods = new Goods("banana", "very sweet");
      post(Path.GOOD, goods);
      response = get(Path.GOOD);
      List<Object> jsonResponse = response.readEntity(List.class);
      assertEquals(1, jsonResponse.size());
   }

   @Test
   public void nameAndDescriptionAreTheSame() {
      DatabaseCleaner.cleanTable("goods");
      Goods goods = new Goods("SweetbananA ", "sweet Banana");
      response = post(Path.GOOD, goods);
      ErrorResult result = response.readEntity(ErrorResult.class);
      String error = result.getError();
      assertEquals(Errors.INVALID_GOODS_NAME.getErrorName(), error);
   }
   
}
