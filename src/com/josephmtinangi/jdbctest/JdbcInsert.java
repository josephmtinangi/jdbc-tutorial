package com.josephmtinangi.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcInsert {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String name;
		String email;
		String password;

		System.out.println("Name");
		name = input.nextLine();
		System.out.println("Email");
		email = input.nextLine();
		System.out.println("Password");
		password = input.nextLine();

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cda", "root", "");
			System.out.println("Connected");
			stmt = conn.createStatement();

			int rowsAffected = stmt.executeUpdate("INSERT INTO users (name, email, password) VALUES('" + name + "', '"
					+ email + "', '" + password + "')");
			System.out.println("Affected = " + rowsAffected);

			rs = stmt.executeQuery("SELECT users.* FROM users");

			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		input.close();
	}

}
