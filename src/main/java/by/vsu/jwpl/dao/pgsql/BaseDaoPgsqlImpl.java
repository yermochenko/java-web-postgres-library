package by.vsu.jwpl.dao.pgsql;

import java.sql.Connection;

abstract public class BaseDaoPgsqlImpl {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	protected Connection getConnection() {
		return connection;
	}
}
