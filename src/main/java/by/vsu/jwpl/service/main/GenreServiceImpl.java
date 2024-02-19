package by.vsu.jwpl.service.main;

import by.vsu.jwpl.dao.DaoException;
import by.vsu.jwpl.dao.GenreDao;
import by.vsu.jwpl.domain.Genre;
import by.vsu.jwpl.service.GenreService;
import by.vsu.jwpl.service.ServiceException;

import java.util.List;

public class GenreServiceImpl implements GenreService {
	private GenreDao genreDao;

	public void setGenreDao(GenreDao genreDao) {
		this.genreDao = genreDao;
	}

	@Override
	public List<Genre> findAll() throws ServiceException {
		try {
			return genreDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Genre findById(Integer id) throws ServiceException {
		try {
			return genreDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Genre genre) throws ServiceException {
		try {
			if(genre.getId() != null) {
				genreDao.update(genre);
			} else {
				genreDao.create(genre);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		try {
			genreDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
