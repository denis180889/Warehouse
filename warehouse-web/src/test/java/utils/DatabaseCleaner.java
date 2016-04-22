package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.util.PropertiesContext;

public class DatabaseCleaner {
   private static Connection conn;
   private static String jdbcUrl = PropertiesContext.getPropertiesContext().getProperty("jdbc.url");;
   private static String jdbcUsername = PropertiesContext.getPropertiesContext().getProperty("jdbc.username");;
   private static String jdbcPassword = PropertiesContext.getPropertiesContext().getProperty("jdbc.password");;


   private static void getConnection() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void cleanTable(String tableName) {
      String cleanTableSQL = "TRUNCATE " + tableName;
      try {
         getConnection();
         if(conn.isClosed()){
            getConnection();
         }
         Statement statement = conn.createStatement();
         statement.executeUpdate(cleanTableSQL);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
