package by.vsu.jwpl.dao.pgsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	public static void init() throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/book_library_db", "root", "root");
	}
}
