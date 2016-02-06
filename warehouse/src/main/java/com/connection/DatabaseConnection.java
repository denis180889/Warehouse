package com.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.entities.Item;
import com.mysql.*;

public class DatabaseConnection {

	Connection conn;
	Statement statement;

	public DatabaseConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Properties property = new Properties();
		try {
			FileInputStream fis = new FileInputStream("C:/Users/Denis/warehouse.properties");
			property.load(fis);
		} catch (IOException e) {
			System.err.println("Error: Property file isn't present!");
		}
		conn = DriverManager.getConnection(property.getProperty("jdbc.url"), property.getProperty("root"), property.getProperty("root"));
	}

	public int insertRowInItemsTable(String name, String description, float longitude, float latitude, int capacity)
			throws SQLException {
		String insertTableSQL = "INSERT INTO item (name, description, longitude, latitude, capacity) " + "VALUES"
				+ "(?,?,?,?,?)";

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
	
	public List<Item> getAllItems() throws SQLException{
		List<Item> allItems = new ArrayList<Item>();
		ResultSet rs = null;
		
		String query = "SELECT * FROM item";
		statement = conn.createStatement();
		rs = statement.executeQuery(query);
		
		 while (rs.next()) {
             Item item = new Item();
             item.setName(rs.getString("name"));
             item.setDescription(rs.getString("description"));
             item.setLatitude(rs.getFloat("longitude"));
             item.setLongitude(rs.getFloat("latitude"));
             item.setCapacity((rs.getInt("capacity")));

             //add each item to the list
             allItems.add(item);
         }
		
		return allItems;
	}
	
}
