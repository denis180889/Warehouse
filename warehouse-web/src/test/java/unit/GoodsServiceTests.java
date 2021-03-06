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
   GoodsDao goodsDao;
   
   @InjectMocks
   GoodsService goodsService;
   
   @Before
   public void initializeMocks(){
      MockitoAnnotations.initMocks(this);
   }
   
   @Test
   public void saveGood(){
      Goods goods = new Goods("banana", "very sweet");
      
      when(goodsDao.save(any(GoodsEntity.class))).thenReturn(1L);
      Long id = goodsService.saveGood(goods);
      assertEquals(new Long(1), id);
   }
   
   @Test
   public void getGoods(){
      List<GoodsEntity> listGe = Arrays.asList(new GoodsEntity("name1", "desc1"), new GoodsEntity("name2", "desc2"));
      
      when(goodsDao.list()).thenReturn(listGe);
      List<Goods> listG = goodsService.getGoods();
      
      assertEquals(listG.size(), listGe.size());
      for(int i =0; i<2; i++){
            assertEquals(listGe.get(i).getName(), listG.get(i).getName());
            assertEquals(listGe.get(i).getDescription(), listG.get(i).getDescription());
         }
      }

}

