package by.vsu.jwpl.dao;

import by.vsu.jwpl.domain.Genre;

import java.util.List;

public interface GenreDao extends Dao<Genre> {
	List<Genre> readAll() throws DaoException;
}
