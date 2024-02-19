package by.vsu.jwpl.service.main;

import by.vsu.jwpl.dao.GenreDao;
import by.vsu.jwpl.dao.pgsql.DatabaseConnector;
import by.vsu.jwpl.dao.pgsql.GenreDaoPgsqlImpl;
import by.vsu.jwpl.service.GenreService;
import by.vsu.jwpl.service.ServiceException;
import by.vsu.jwpl.service.ServiceLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceLocatorImpl implements ServiceLocator {
	private static final Logger logger = LogManager.getLogger();

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
			try {
				connection = DatabaseConnector.getConnection();
			} catch(SQLException e) {
				logger.error("Can't connect to database", e);
				throw e;
			}
		}
		return connection;
	}

	@Override
	public void close() {}
}
