package by.vsu.jwpl.service;

import by.vsu.jwpl.service.test.ServiceLocatorTestImpl;

public interface ServiceLocator extends AutoCloseable {
	GenreService newGenreServiceInstance() throws ServiceException;

	static ServiceLocator newServiceLocatorInstance() throws ServiceException {
		return new ServiceLocatorTestImpl();
	}
}
