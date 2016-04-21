package integration;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.dto.BaseDTO;
import com.util.PropertiesContext;

import utils.DatabaseCleaner;

public class BaseIntegrationTest {

   protected DatabaseCleaner databaseCleaner;
   protected Response response;
   protected WebTarget target;
   protected String applicationUrl = PropertiesContext.getPropertContext().getProperty("app.url");
   
   public Response createPostConnection(String path, BaseDTO dto) {
      return target.path(path).request(MediaType.APPLICATION_JSON).post(Entity.json(dto));
   }
   
   public Response createGetConnection(String path) {
      return target.path(path).request(MediaType.APPLICATION_JSON).get();
   }
   
}
