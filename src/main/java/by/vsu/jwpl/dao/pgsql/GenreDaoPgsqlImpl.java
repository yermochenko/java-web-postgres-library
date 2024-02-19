package by.vsu.jwpl.dao.pgsql;

import by.vsu.jwpl.dao.DaoException;
import by.vsu.jwpl.dao.GenreDao;
import by.vsu.jwpl.domain.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenreDaoPgsqlImpl extends BaseDaoPgsqlImpl implements GenreDao {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Integer create(Genre genre) throws DaoException {
		String sql = "INSERT INTO \"genre\" (\"name\") VALUES (?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, genre.getName());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			return resultSet.getInt(1);
		} catch(SQLException e) {
			logger.error("Can't execute SQL: {}", sql, e);
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(resultSet).close(); } catch(Exception ignored) {}
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
	}

	@Override
	public List<Genre> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"name\" FROM \"genre\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection().createStatement();
			resultSet = statement.executeQuery(sql);
			List<Genre> genres = new ArrayList<>();
			while(resultSet.next()) {
				Genre genre = new Genre();
				genre.setId(resultSet.getInt("id"));
				genre.setName(resultSet.getString("name"));
				genres.add(genre);
			}
			return genres;
		} catch(SQLException e) {
			logger.error("Can't execute SQL: {}", sql, e);
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(resultSet).close(); } catch(Exception ignored) {}
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
	}

	@Override
	public Genre read(Integer id) throws DaoException {
		String sql = "SELECT \"id\", \"name\" FROM \"genre\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection().prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			Genre genre = null;
			if(resultSet.next()) {
				genre = new Genre();
				genre.setId(resultSet.getInt("id"));
				genre.setName(resultSet.getString("name"));
			}
			return genre;
		} catch(SQLException e) {
			logger.error("Can't execute SQL: {}", sql, e);
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(resultSet).close(); } catch(Exception ignored) {}
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
	}

	@Override
	public void update(Genre genre) throws DaoException {
		String sql = "UPDATE \"genre\" SET \"name\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(sql);
			statement.setString(1, genre.getName());
			statement.setInt(2, genre.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			logger.error("Can't execute SQL: {}", sql, e);
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
	}

	@Override
	public void delete(Integer id) throws DaoException {
		String sql = "DELETE FROM \"genre\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch(SQLException e) {
			logger.error("Can't execute SQL: {}", sql, e);
			throw new DaoException(e);
		} finally {
			try { Objects.requireNonNull(statement).close(); } catch(Exception ignored) {}
		}
	}
}
