package utils;

public class Path {

   private Path() {
      //constructor to hide the implicit public one
   }
   
   public final static String GOOD = "/good";
   public final static String WAREHOUSE = "/warehouse";
   public final static String WAREHOUSE_GOODS_ADD = "/warehouse/goods/add";
   public final static String WAREHOUSE_GOODS_INCREASE = "/warehouse/goods/increase";
   public final static String WAREHOUSE_GOODS_REMOVE = "/warehouse/goods/remove";
}
