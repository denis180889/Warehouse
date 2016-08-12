package integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import org.junit.Before;
import org.junit.Test;

import com.dto.Goods;
import com.dto.common.ErrorResult;
import com.dto.common.SingleResult;

import utils.DatabaseUtil;
import utils.Errors;
import utils.Path;

public class GoodsResourceIntegrationTests extends BaseIntegrationTest {
   
   @Before
   public void createClient() throws ClassNotFoundException, SQLException {
      Client client = ClientBuilder.newClient();
      this.target = client.target(applicationUrl);
   }

   @Test
   public void createGoods() {
      DatabaseUtil.cleanTable("goods");
      Goods goods = new Goods("banana", "very sweet");
      response = post(Path.GOOD, goods);
      SingleResult result = response.readEntity(SingleResult.class);
      long id = result.getId();
      assertEquals("Id in response was wrong", 1, id);
      assertEquals("Response was wrong",200 , response.getStatus());
      List<Goods> existingGoods = get(Path.GOOD).get(new GenericType<List<Goods>>() {});
      assertEquals("Goods size was incorrect in database", 1, existingGoods.size());
      assertEquals("Goods name was incorrect in database", "banana", existingGoods.get(0).getName());
      assertEquals("Goods description was incorrect in database", "very sweet", existingGoods.get(0).getDescription());
   }

   @Test
   public void getGoods() {
      DatabaseUtil.cleanTable("goods");
      Goods goods1 = new Goods("banana", "very sweet");
      Goods goods2 = new Goods("kiwi", "very tasty");
      post(Path.GOOD, goods1);
      post(Path.GOOD, goods2);
      List<Goods> existingGoods = get(Path.GOOD).get(new GenericType<List<Goods>>() {});
      assertEquals("Amount of goods was wrong", 2, existingGoods.size());
      assertTrue("Good1 id was wrong", existingGoods.get(0).getId().equals(1L));
      assertTrue("Good2 id was wrong", existingGoods.get(1).getId().equals(2L));
   }

   @Test
   public void nameAndDescriptionAreTheSame() {
      DatabaseUtil.cleanTable("goods");
      Goods goods = new Goods("SweetbananA ", "sweet Banana");
      response = post(Path.GOOD, goods);
      ErrorResult result = response.readEntity(ErrorResult.class);
      String error = result.getError();
      assertEquals(Errors.INVALID_GOODS_NAME.getErrorName(), error);
   }
   
}
