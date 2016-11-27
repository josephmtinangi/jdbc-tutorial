package com.josephmtinangi.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcPreparedStament {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/cda";
		String username = "root";
		String password = "";

		String name;
		String email;

		System.out.println("Name");
		name = input.nextLine();
		System.out.println("Email");
		email = input.nextLine();

		try {
			conn = DriverManager.getConnection(dbUrl, username, password);

			String sql = "SELECT users.* FROM users WHERE name = ? AND email = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, name);
			stmt.setString(2, email);

			rs = stmt.executeQuery();

			displayUser(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		input.close();
	}

	private static void displayUser(ResultSet rs) {
		try {
			while (rs.next()) {
				System.out.println(rs.getString("name") + ", " + rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
