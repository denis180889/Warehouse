package unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dao.GoodsDao;
import com.dto.Goods;
import com.entities.GoodsEntity;
import com.service.GoodsService;

public class GoodsServiceTests {

   @Mock
   GoodsDao goodDao;
   
   @InjectMocks
   GoodsService goodsService;
   
   @Before
   public void initializeMocks(){
      MockitoAnnotations.initMocks(this);
   }
   
   @Test
   public void saveGood(){
      Goods good = new Goods("banana", "very sweet");
      
      when(goodDao.save(any(GoodsEntity.class))).thenReturn(1L);
      long id = goodsService.saveGood(good);
      assertEquals(1L, id);
   }
   
   @Test
   public void getGoods(){
      List<GoodsEntity> listGe = Arrays.asList(new GoodsEntity("name1", "desc1"), new GoodsEntity("name2", "desc2"));
      
      when(goodDao.list()).thenReturn(listGe);
      List<Goods> listG = goodsService.getGoods();
      
      assertEquals(listG.size(), listGe.size());
      for(int i =0; i<2; i++){
            assertEquals(listGe.get(i).getName(), listG.get(i).getName());
            assertEquals(listGe.get(i).getDescription(), listG.get(i).getDescription());
         }
      }

}

