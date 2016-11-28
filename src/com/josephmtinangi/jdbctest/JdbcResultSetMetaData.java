package com.josephmtinangi.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcResultSetMetaData {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM film");

			ResultSetMetaData resultSetMetaData = rs.getMetaData();

			int columnCount = resultSetMetaData.getColumnCount();
			System.out.println("COLUMNS: " + columnCount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
