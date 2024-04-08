package by.vsu.jwpl.web;

import by.vsu.jwpl.domain.Genre;
import by.vsu.jwpl.service.GenreService;
import by.vsu.jwpl.service.ServiceLocator;
import com.fasterxml.jackson.core.JsonProcessingException;
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
			ObjectMapper mapper = new ObjectMapper();
			String id = req.getParameter("id");
			if(id == null) {
				List<Genre> genres = genreService.findAll();
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				mapper.writeValue(resp.getOutputStream(), genres);
			} else {
				try {
					Genre genre = genreService.findById(Integer.valueOf(id));
					if(genre != null) {
						resp.setStatus(HttpServletResponse.SC_OK);
						resp.setContentType("application/json");
						resp.setCharacterEncoding("UTF-8");
						mapper.writeValue(resp.getOutputStream(), genre);
					} else {
						throw new IllegalArgumentException();
					}
				} catch(IllegalArgumentException e) {
					resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
			}
		} catch(JsonProcessingException e) {
			throw new ServletException(e);
		} catch(IOException e) {
			throw e;
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(ServiceLocator serviceLocator = ServiceLocator.newServiceLocatorInstance()) {
			ObjectMapper mapper = new ObjectMapper();
			Genre genre = mapper.readValue(req.getInputStream(), Genre.class);
			GenreService genreService = serviceLocator.getGenreServiceInstance();
			genreService.save(genre);
			resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} catch(JsonProcessingException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		} catch(IOException e) {
			throw e;
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if(id != null) {
			try(ServiceLocator serviceLocator = ServiceLocator.newServiceLocatorInstance()) {
				GenreService genreService = serviceLocator.getGenreServiceInstance();
				try { genreService.delete(Integer.valueOf(id)); } catch(NumberFormatException ignored) {}
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} catch(Exception e) {
				throw new ServletException(e);
			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
