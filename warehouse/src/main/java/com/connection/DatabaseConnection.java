package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.*;

public class DatabaseConnection {

	Connection conn;
	Statement statement;
	
	public DatabaseConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "root", "root");
		
	}
	
	public int insertRowInItemsTable(String name, String description, float longitude, float latitude, int capacity) throws SQLException{
		String insertTableSQL = "INSERT INTO item (name, description, longitude, latitude, capacity) " + "VALUES" + "(?,?,?,?,?)";
		
		PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, description);
		preparedStatement.setFloat(3, longitude);
		preparedStatement.setFloat(4, latitude);
		preparedStatement.setInt(5, capacity);
		
		// execute insert SQL stetement
		preparedStatement .executeUpdate();
		
		ResultSet generatedKeys = preparedStatement.getGeneratedKeys(); 
        generatedKeys.next(); 
        return generatedKeys.getInt(1);
	}
}
