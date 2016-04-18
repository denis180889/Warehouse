package unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.dao.WarehouseDao;
import com.dto.Warehouse;
import com.entities.WarehouseEntity;
import com.service.WarehouseService;

public class WarehouseServiceTest {

   @Mock
   WarehouseDao warehouseDao; 
   WarehouseService warehouseService;
   
   @Before
   public void initializeMocks(){
      MockitoAnnotations.initMocks(this);
      warehouseService = new WarehouseService();
      warehouseService.setWarehouseDao(warehouseDao);
   }
   
   @Test
   public void saveWarehouseTest(){
      Warehouse warehouse = new Warehouse("name", "desc", 1, 1, 1);
      
      when(warehouseDao.save(any(WarehouseEntity.class))).thenReturn(1L);
      long id = warehouseService.saveWarehouse(warehouse);
      assertEquals(1L, id);
   }
   
   @Test
   public void getWarehousesTest(){
      List<WarehouseEntity> listWe = Arrays.asList(new WarehouseEntity("name1", "desc1",1,1,1), new WarehouseEntity("name2", "desc2",2,2,2));
      
      when(warehouseDao.list()).thenReturn(listWe);
      List<Warehouse> listW = warehouseService.getWarehouses();
      
      assertEquals(listW.size(), listWe.size());
      for(int i =0; i<2; i++){
            assertEquals(listWe.get(i).getName(), listW.get(i).getName());
            assertEquals(listWe.get(i).getDescription(), listW.get(i).getDescription());
         }
      }
}
