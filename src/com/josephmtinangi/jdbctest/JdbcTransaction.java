package com.josephmtinangi.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTransaction {

	public static void main(String[] args) {
		Connection conn = null;

		String dbUrl = "jdbc:mysql://localhost:3306/sakila";
		String username = "root";
		String password = "";

		int filmLength = 184;
		int rows = 10;

		try {
			// 1. Get a connection to the database
			conn = DriverManager.getConnection(dbUrl, username, password);

			// Turn off auto commit
			conn.setAutoCommit(false);

			// show data before
			System.out.println("BEFORE");
			showFilmLength(conn, filmLength, rows);

			System.out.println("BEGIN TRANSACTION");

			// update
			Statement stmtUpdate1 = null;
			stmtUpdate1 = conn.createStatement();
			int new_length = 195;
			String sqlUpdate1 = "UPDATE film SET length = '" + new_length + "' WHERE length > '" + filmLength + "'";
			int rowsUpdated1 = stmtUpdate1.executeUpdate(sqlUpdate1);
			System.out.println("ROWS UPDATED = " + rowsUpdated1);

			// update
			PreparedStatement stmtUpdate2 = null;
			String sqlUpdate2 = "UPDATE film SET rental_duration = ? WHERE rental_duration > ?";
			stmtUpdate2 = conn.prepareStatement(sqlUpdate2);
			stmtUpdate2.setInt(1, 10);
			stmtUpdate2.setInt(2, 5);

			int rowsUpdated2 = stmtUpdate2.executeUpdate();

			System.out.println("ROWS UPDATED 2 = " + rowsUpdated2);

			System.out.println("TRANSACTION STEPS ARE READY");

			// Ask user if it is okay to save
			boolean ok = askUserIfOkToSave();
			if (ok) {
				// store in database
				conn.commit();
				System.out.println("TRANSACTION COMMITED");
			} else {
				// discard
				conn.commit();
				System.out.println("TRANSACTION ROLLED BACK");
			}

			System.out.println("AFTER");
			showFilmLength(conn, filmLength, rows);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static boolean askUserIfOkToSave() {
		Scanner input = new Scanner(System.in);

		boolean response = false;

		String answer;
		System.out.println("Is it okay to save?yes/no[no]");
		answer = input.nextLine();

		switch (answer) {
		case "yes":
			response = true;
			break;
		case "no":
			response = false;
		default:
			response = false;
			break;
		}

		input.close();
		return response;
	}

	private static void showFilmLength(Connection conn, int filmLength, int rows) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select film.film_id, film.title, film.length, film.rating, film.rental_duration from film where film.length > ? limit ?;";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmLength);
			stmt.setInt(2, rows);

			rs = stmt.executeQuery();

			System.out.println("ID\tTitle\t\t\tLength\tRating\t\tRental duration");
			while (rs.next()) {
				System.out.println(rs.getInt("film_id") + "\t" + rs.getString("title") + "\t\t" + rs.getInt("length")
						+ "\t" + rs.getString("rating") + "\t" + rs.getInt("rental_duration"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
