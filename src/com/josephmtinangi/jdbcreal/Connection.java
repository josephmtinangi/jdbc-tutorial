package com.josephmtinangi.jdbcreal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connection {

	public static void main(String args[]) {
		java.sql.Connection conn;

		Properties props = new Properties();
		try {
			// Load the properties
			props.load(new FileInputStream("connection.properties"));

			// Read the properties
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			String dbUrl = props.getProperty("dbUrl");

			System.out.println("Connecting to database...");
			System.out.println("username = " + username);
			System.out.println("password = " + password);
			System.out.println("dbUrl = " + dbUrl);

			try {
				conn = DriverManager.getConnection(dbUrl, username, password);

				System.out.println("Connection Successful.");
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
