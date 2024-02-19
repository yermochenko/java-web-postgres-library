package by.vsu.jwpl.service.main;

import by.vsu.jwpl.dao.GenreDao;
import by.vsu.jwpl.dao.pgsql.DatabaseConnector;
import by.vsu.jwpl.dao.pgsql.GenreDaoPgsqlImpl;
import by.vsu.jwpl.service.GenreService;
import by.vsu.jwpl.service.ServiceException;
import by.vsu.jwpl.service.ServiceLocator;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceLocatorImpl implements ServiceLocator {
	private GenreService genreService;
	@Override
	public GenreService getGenreServiceInstance() throws ServiceException {
		if(genreService == null) {
			try {
				GenreServiceImpl genreServiceImpl = new GenreServiceImpl();
				genreServiceImpl.setGenreDao(getGenreDaoInstance());
				genreService = genreServiceImpl;
			} catch(SQLException e) {
				throw new ServiceException(e);
			}
		}
		return genreService;
	}

	private GenreDao genreDao;
	private GenreDao getGenreDaoInstance() throws SQLException {
		if(genreDao == null) {
			GenreDaoPgsqlImpl genreDaoPgsqlImpl = new GenreDaoPgsqlImpl();
			genreDaoPgsqlImpl.setConnection(getConnection());
			genreDao = genreDaoPgsqlImpl;
		}
		return genreDao;
	}

	private Connection connection;
	private Connection getConnection() throws SQLException {
		if(connection == null) {
			connection = DatabaseConnector.getConnection();
		}
		return connection;
	}

	@Override
	public void close() {}
}
