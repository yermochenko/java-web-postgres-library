package by.vsu.jwpl.service.test;

import by.vsu.jwpl.service.GenreService;
import by.vsu.jwpl.service.ServiceLocator;

public class ServiceLocatorTestImpl implements ServiceLocator {
	@Override
	public GenreService newGenreServiceInstance() {
		return new GenreServiceTestImpl();
	}

	@Override
	public void close() {}
}
