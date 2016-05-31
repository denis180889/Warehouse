package utils;

public enum Errors {
   INVALID_WAREHOUSE_NAME("INVALID_WAREHOUSE_NAME"),
   INVALID_LATITUDE("INVALID_LATITUDE"),
   INVALID_LONGTITUDE("INVALID_LONGTITUDE"),
   INVALID_GOODS_NAME("INVALID_GOODS_NAME")
   ;
   private String errorName;

   private Errors(String errorName) {
      this.errorName = errorName;
   }

   public String getErrorName() {
      return errorName;
   }
}
