package com.josephmtinangi.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcUpdate {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/cda";
		String username = "root";
		String password = "";

		String new_name = "";
		String old_name = "";

		System.out.println("Old name");
		old_name = input.nextLine();
		System.out.println("New name");
		new_name = input.nextLine();

		try {
			conn = DriverManager.getConnection(dbUrl, username, password);

			stmt = conn.createStatement();

			System.out.println("BEFORE UPDATE");
			displayEmployee(conn, old_name);

			int rowsAffected = stmt
					.executeUpdate("UPDATE users SET name = '" + new_name + "' WHERE name='" + old_name + "'");

			if (rowsAffected == 1) {
				System.out.println(old_name + " successfully changed to " + new_name);
			} else {
				System.out.println("No name matches " + old_name);
			}

			System.out.println("AFTER UPDATE");
			displayEmployee(conn, new_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

		input.close();

	}

	private static void displayEmployee(Connection conn, String name) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT users.* FROM users WHERE name='" + name + "'");

			while (rs.next()) {
				System.out.println(rs.getString("name") + "," + rs.getString("email"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
