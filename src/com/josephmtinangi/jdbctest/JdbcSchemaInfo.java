package com.josephmtinangi.jdbctest;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcSchemaInfo {

	public static void main(String[] args) {

		Connection conn = null;
		ResultSet rs = null;

		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String[] types = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "");

			DatabaseMetaData databaseMetaData = conn.getMetaData();

			rs = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);

			System.out.println("TABLES");
			while (rs.next()) {
				System.out.println(rs.getString("TABLE_NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
