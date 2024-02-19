package by.vsu.jwpl.dao;

import by.vsu.jwpl.domain.Entity;

public interface Dao<T extends Entity> {
	Integer create(T entity) throws DaoException;
	T read(Integer id) throws DaoException;
	void update(T entity) throws DaoException;
	void delete(Integer id) throws DaoException;
}
