package utils;

public enum Errors {
   INVALID_WAREHOUSE_NAME("INVALID_WAREHOUSE_NAME"),
   INVALID_LATITUDE("INVALID_LATITUDE"),
   INVALID_LONGTITUDE("INVALID_LONGTITUDE"),
   INVALID_GOODS_NAME("INVALID_GOODS_NAME"),
   NON_EXISTING_WAREHOUSE_ID("NON_EXISTING_WAREHOUSE_ID"),
   NON_EXISTING_GOOD_ID("NON_EXISTING_GOOD_ID"),
   NON_EXISTING_GOOD_ON_WAREHOUSE("NON_EXISTING_GOOD_ON_WAREHOUSE")
   ;
   private String errorName;

   private Errors(String errorName) {
      this.errorName = errorName;
   }

   public String getErrorName() {
      return errorName;
   }
}
