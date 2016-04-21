package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCleaner {
   Connection conn;
   Statement statement;

   public DatabaseCleaner() throws ClassNotFoundException, SQLException {
      Class.forName("com.mysql.jdbc.Driver");
//      conn = DriverManager.getConnection("jdbc:mysql://33.33.33.1:1488/warehouse", "root", "root");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
   }

   public void cleanTable(String tableName) throws SQLException {
      String cleanTableSQL = "TRUNCATE "+tableName;
      Statement statement  = conn.createStatement();
      statement.executeUpdate(cleanTableSQL);
   }
   
   public void closeConnection() throws SQLException{
      conn.close();
   }  
}
