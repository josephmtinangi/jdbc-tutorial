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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cda", "root", "");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM users");

			ResultSetMetaData resultSetMetaData = rs.getMetaData();

			int columnCount = resultSetMetaData.getColumnCount();
			System.out.println("COLUMN COUNT: " + columnCount);

			System.out.println("COLUMNS");
			for (int column = 1; column < columnCount; column++) {
				System.out.print("\nNAME:" + resultSetMetaData.getColumnName(column) + "\nTYPE: "
						+ resultSetMetaData.getColumnTypeName(column) + "\nIS NULLABLE: "
						+ resultSetMetaData.isNullable(column) + "\nIS AUTO INCREMENT: "
						+ resultSetMetaData.isAutoIncrement(column) + "\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
