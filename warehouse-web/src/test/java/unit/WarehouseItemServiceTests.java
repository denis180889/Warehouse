package unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.dao.WarehouseItemDao;
import com.dto.WarehouseItem;
import com.entities.WarehouseItemEntity;
import com.service.WarehouseItemService;

public class WarehouseItemServiceTests {
      
   @Mock
   WarehouseItemDao warehouseItemDao; 
   WarehouseItemService warehouseItemService;
   
   
   @Before
   public void initializeMocks(){
      MockitoAnnotations.initMocks(this);
      warehouseItemService = new WarehouseItemService();
      warehouseItemService.setWarehouseItemDao(warehouseItemDao);
   }
   
   @Test
   public void addGoodToWarehouseTest(){
      WarehouseItem warehouse = new WarehouseItem(1, 1, 1);
      
      when(warehouseItemDao.save(any(WarehouseItemEntity.class))).thenReturn(1L);
      long id = warehouseItemService.addGoodToWarehouse(warehouse);
      assertEquals(1L, id);
   }
   
   @Test
   public void removeGoodFromWarehouseTest(){
      
      when(warehouseItemDao.decreaseAmount(any(Long.class),any(Integer.class))).thenReturn(1L);
      long id = warehouseItemService.removeGoodFromWarehouse(1, 1);
      assertEquals(1L, id);
   }
   
}
