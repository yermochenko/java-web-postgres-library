package by.vsu.jwpl.service.test;

import by.vsu.jwpl.domain.Genre;
import by.vsu.jwpl.service.GenreService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenreServiceTestImpl implements GenreService {
	private final static Map<Integer, Genre> genres = new HashMap<>();
	static {
		Genre genre;
		genre = new Genre();
		genre.setId(1);
		genre.setName("Classic");
		genres.put(genre.getId(), genre);
		genre = new Genre();
		genre.setId(2);
		genre.setName("Fantasy");
		genres.put(genre.getId(), genre);
		genre = new Genre();
		genre.setId(3);
		genre.setName("Detective");
		genres.put(genre.getId(), genre);
	}

	@Override
	public List<Genre> findAll() {
		return genres.values().stream().sorted(Comparator.comparing(Genre::getName)).collect(Collectors.toList());
	}

	@Override
	public Genre findById(Integer id) {
		return genres.get(id);
	}

	@Override
	public void save(Genre genre) {
		if(genre.getId() != null) {
			// update
			if(genres.containsKey(genre.getId())) {
				genres.put(genre.getId(), genre);
			}
		} else {
			// create
			Integer id = genres.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
			genre.setId(id);
			genres.put(id, genre);
		}
	}

	@Override
	public void delete(Integer id) {
		genres.remove(id);
	}
}
