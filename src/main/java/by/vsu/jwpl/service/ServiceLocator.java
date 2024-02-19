package by.vsu.jwpl.service;

import by.vsu.jwpl.service.main.ServiceLocatorImpl;

public interface ServiceLocator extends AutoCloseable {
	GenreService getGenreServiceInstance() throws ServiceException;

	static ServiceLocator newServiceLocatorInstance() {
		return new ServiceLocatorImpl();
	}
}
