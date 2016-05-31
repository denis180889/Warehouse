package unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dao.WarehouseItemDao;
import com.dto.WarehouseItem;
import com.entities.WarehouseItemEntity;
import com.service.WarehouseItemService;

public class WarehouseItemServiceTests {
      
   @Mock
   WarehouseItemDao warehouseItemDao; 
   
   @InjectMocks
   WarehouseItemService warehouseItemService;
   
   
   @Before
   public void initializeMocks(){
      MockitoAnnotations.initMocks(this);
   }
   
   @Test
   public void addGoodToWarehouse(){
      WarehouseItem warehouse = new WarehouseItem(1, 1, 1);
      
      when(warehouseItemDao.save(any(WarehouseItemEntity.class))).thenReturn(1L);
      long id = warehouseItemService.addGoodToWarehouse(warehouse);
      assertEquals(1L, id);
   }
   
   @Test
   public void removeGoodFromWarehouse(){
      
      when(warehouseItemDao.decreaseAmount(any(Long.class),any(Integer.class))).thenReturn(1L);
      long id = warehouseItemService.removeGoodFromWarehouse(1, 1);
      assertEquals(1L, id);
   }
   
}
