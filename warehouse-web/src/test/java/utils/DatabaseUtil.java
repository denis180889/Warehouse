package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.PropertiesContext;

public class DatabaseUtil {
   private static Connection conn;
   private static String jdbcUrl = PropertiesContext.getPropertiesContext().getProperty("jdbc.url");;
   private static String jdbcUsername = PropertiesContext.getPropertiesContext().getProperty("jdbc.username");;
   private static String jdbcPassword = PropertiesContext.getPropertiesContext().getProperty("jdbc.password");;


   private static void getConnection() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
      } catch (Exception e) {
      
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
         conn.close();
      } catch (Exception e) {
        
      }
      finally{
         try {
            conn.close();
         } catch (SQLException e) {
         
         }
      }
   }
   
   public static int getValueInTheRowById(String table, int rowId, String value){
      ResultSet rs = null;
      int result = 0;
      try {
         getConnection();
         if(conn.isClosed()){
            getConnection();
         }
         Statement statement = conn.createStatement();
         rs  = statement.executeQuery("SELECT * FROM " + table + " WHERE id="+rowId);
         rs.last();
         result = rs.getInt("amount");
         conn.close();
      } catch (Exception e) {
         
      }
      finally{
         try {
            conn.close();
         } catch (SQLException e) {
           
         }
      }
      return result;
   }
   
}
