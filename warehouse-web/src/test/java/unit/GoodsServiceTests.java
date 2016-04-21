package unit;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

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
   
   @InjectMocks
   GoodsService goodsService;
   
   @Before
   public void initializeMocks(){
      MockitoAnnotations.initMocks(this);
   }
   
   @Test
   public void saveGood(){
      Good good = new Good("banana", "very sweet");
      
      when(goodDao.save(any(GoodsEntity.class))).thenReturn(1L);
      long id = goodsService.saveGood(good);
      assertEquals(1L, id);
   }
   
   @Test
   public void getGoods(){
      List<GoodsEntity> listGe = Arrays.asList(new GoodsEntity("name1", "desc1"), new GoodsEntity("name2", "desc2"));
      
      when(goodDao.list()).thenReturn(listGe);
      List<Good> listG = goodsService.getGoods();
      
      assertEquals(listG.size(), listGe.size());
      for(int i =0; i<2; i++){
            assertEquals(listGe.get(i).getName(), listG.get(i).getName());
            assertEquals(listGe.get(i).getDescription(), listG.get(i).getDescription());
         }
      }

}

