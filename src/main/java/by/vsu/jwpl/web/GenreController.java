package by.vsu.jwpl.web;

import by.vsu.jwpl.domain.Genre;
import by.vsu.jwpl.service.GenreService;
import by.vsu.jwpl.service.ServiceLocator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GenreController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(ServiceLocator serviceLocator = ServiceLocator.newServiceLocatorInstance()) {
			GenreService genreService = serviceLocator.getGenreServiceInstance();
			List<Genre> genres = genreService.findAll();
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(resp.getOutputStream(), genres);
		} catch(IOException e) {
			throw e;
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}
}
