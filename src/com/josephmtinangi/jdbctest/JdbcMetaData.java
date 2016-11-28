package com.josephmtinangi.jdbctest;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcMetaData {

	public static void main(String[] args) {
		Connection conn = null;

		String dbUrl = "jdbc:mysql://localhost:3306/sakila";
		String username = "root";
		String password = "";

		try {
			conn = DriverManager.getConnection(dbUrl, username, password);

			DatabaseMetaData databaseMetaData = conn.getMetaData();

			System.out.println("Product name: " + databaseMetaData.getDatabaseProductName());
			System.out.println("Product version: " + databaseMetaData.getDatabaseProductVersion());

			System.out.println();

			System.out.println("JDBC Driver name: " + databaseMetaData.getDriverName());
			System.out.println("JDBC Driver version" + databaseMetaData.getDriverVersion());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
