package com.josephmtinangi.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. Get a database connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "");

			System.out.println("Database connection successfully");

			// 2. Create a statement
			stmt = conn.createStatement();

			// 3. Execute SQL query
			rs = stmt.executeQuery("SELECT employees.* FROM employees LIMIT 10");

			// 4. Process the result
			while (rs.next()) {
				System.out.println(rs.getString("first_name") + "\t" + rs.getString("last_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
