package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.util.PropertiesContext;

public class DatabaseUtil {
   private static Connection conn;
   private static String jdbcUrl = PropertiesContext.getPropertiesContext().getProperty("jdbc.url");;
   private static String jdbcUsername = PropertiesContext.getPropertiesContext().getProperty("jdbc.username");;
   private static String jdbcPassword = PropertiesContext.getPropertiesContext().getProperty("jdbc.password");;
   
   static Logger logger = Logger.getAnonymousLogger();
   
   private DatabaseUtil() {
      //constructor to hide the implicit public one
   }
   
   private static void getConnection() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
      } catch (Exception e) {
         logger.log(Level.SEVERE, "an exception was thrown", e);
      }
   }

   public static void cleanTable(String tableName) {
      String cleanTableSQL = "TRUNCATE " + tableName;
      Statement statement = null;
      try {
         getConnection();
         if(conn.isClosed()){
            getConnection();
         }
         statement = conn.createStatement();
         statement.executeUpdate(cleanTableSQL);
         statement.close();
      } catch (Exception e) {
         logger.log(Level.SEVERE, "an exception was thrown", e);
      }
      finally{
         try {
            if(statement != null){
            statement.close();
            }
         } catch (SQLException e) {
            logger.log(Level.SEVERE, "an exception was thrown", e);
         }
      }
   }
   
   public static int getValueInTheRowById(String table, int rowId, String value){
      ResultSet rs = null;
      int result = 0;
      PreparedStatement preparedStatement = null;
      String query = "SELECT * FROM "+table+" WHERE id=?";
      try {
         if(conn == null){
            getConnection();
         }
         preparedStatement = conn.prepareStatement(query);
         preparedStatement.setInt(1, rowId);
         rs  = preparedStatement.executeQuery();
         rs.last();
         result = rs.getInt(value);
         rs.close();
         preparedStatement.close();
      } catch (Exception e) {
         logger.log(Level.SEVERE, "an exception was thrown", e);
      }
      finally{
         try {
            if(preparedStatement != null){
               preparedStatement.close();
            }
            if(rs != null){
            rs.close();
            }
         } catch (SQLException e) {
            logger.log(Level.SEVERE, "an exception was thrown", e);
         }
      }
      return result;
   }
   
}
