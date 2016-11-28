package com.josephmtinangi.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcDelete {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Connection conn = null;

		String dbUrl = "jdbc:mysql://localhost:3306/cda";
		String username = "root";
		String password = "";

		try {
			conn = DriverManager.getConnection(dbUrl, username, password);

			String email;
			int choice;
			System.out.println("Email address");
			email = input.nextLine();

			System.out.println("1. Display");
			System.out.println("2. Delete");
			choice = input.nextInt();

			if (choice == 1) {
				displayUser(conn, email);
			} else if (choice == 2) {
				deleteUser(conn, email);
			} else {
				System.out.println("Invalid choice");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		input.close();
	}

	private static void deleteUser(Connection conn, String email) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "DELETE FROM users WHERE email = '" + email + "'";
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected == 1) {
				System.out.println("User with email = " + email + " deleted successfully.");
			} else {
				System.out.println("No user matches with that email address.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void displayUser(Connection conn, String email) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT users.* FROM users WHERE email = '" + email + "'");

			while (rs.next()) {
				System.out.println(rs.getString("name") + ", " + rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
