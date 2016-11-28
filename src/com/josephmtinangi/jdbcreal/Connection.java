package com.josephmtinangi.jdbcreal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Connection {

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		java.sql.Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

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

				int length = 0;
				String comp_op = "=";

				System.out.println("(<, >, =)");
				comp_op = input.nextLine();
				System.out.print("Length:");
				length = input.nextInt();

				String sql;
				if (comp_op.equals(">")) {
					sql = "SELECT film.* FROM film WHERE film.length > ?";
				} else if (comp_op.equals("<")) {
					sql = "SELECT film.* FROM film WHERE film.length < ?";
				} else {
					sql = "SELECT film.* FROM film WHERE film.length = ?";
				}

				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, length);

				rs = stmt.executeQuery();

				while (rs.next()) {
					System.out.println(rs.getInt("film_id") + "\t" + rs.getString("title") + "\t\t"
							+ rs.getString("description") + "\t\t" + rs.getInt("length"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		input.close();
	}
}
