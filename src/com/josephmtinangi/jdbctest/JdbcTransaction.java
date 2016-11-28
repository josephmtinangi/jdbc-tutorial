package com.josephmtinangi.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTransaction {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		String dbUrl = "jdbc:mysql://localhost:3306/sakila";
		String username = "root";
		String password = "";

		int filmLength = 120;
		int rows = 10;

		try {
			// 1. Get a connection to the database
			conn = DriverManager.getConnection(dbUrl, username, password);

			// Turn off auto commit
			conn.setAutoCommit(false);

			// show data before
			showFilmLength(conn, filmLength, rows);

			// delete all films with length greater than 120 minutes

			// set all films with rating of PG-13 to PG-14

			// Ask user if it is okay to save
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void showFilmLength(Connection conn, int filmLength, int rows) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select film.film_id, film.title, film.length, film.rating from film where film.length > ? limit ?;";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmLength);
			stmt.setInt(2, rows);

			rs = stmt.executeQuery();

			System.out.println("ID\tTitle\t\t\tLength\tRating");
			while (rs.next()) {
				System.out.println(rs.getInt("film_id") + "\t" + rs.getString("title") + "\t\t" + rs.getInt("length")
						+ "\t" + rs.getString("rating"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
