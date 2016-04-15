package unit;

import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import com.dao.GoodDao;
import com.dto.Good;
import com.entities.GoodsEntity;
import com.service.GoodsService;
import org.junit.Before;
import org.junit.Test;

public class GoodsServiceTests {

   @Mock
   GoodDao goodDao;
   
   GoodsService goodsService;
   GoodsEntity entity;
   Good good;
   
   @Before
   public void initializeMocks(){
      MockitoAnnotations.initMocks(this);
      goodsService = new GoodsService();
      good = new Good("banana", "very sweet");
      entity = new GoodsEntity(good.getName(), good.getDescription());
   }
   
   @Test
   public void saveGoodTest(){
      goodsService.setGoodDao(goodDao);
      
      when(goodDao.save(entity)).thenReturn(1L);
      long id = goodsService.saveGood(good);
      assertEquals(1L, id);
      
      
   }
   
}
