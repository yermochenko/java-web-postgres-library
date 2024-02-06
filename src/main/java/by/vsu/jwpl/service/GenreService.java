package by.vsu.jwpl.service;

import by.vsu.jwpl.domain.Genre;

import java.util.List;

public interface GenreService {
	List<Genre> findAll() throws ServiceException;
	Genre findById(Integer id) throws ServiceException;
	void save(Genre genre) throws ServiceException;
	void delete(Integer id) throws ServiceException;
}
