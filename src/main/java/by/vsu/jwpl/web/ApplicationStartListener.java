package by.vsu.jwpl.web;

import by.vsu.jwpl.dao.pgsql.DatabaseConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationStartListener implements ServletContextListener {
	private static final Logger logger = LogManager.getLogger();
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			DatabaseConnector.init();
			logger.info("JDBC diver class loaded successfully");
		} catch(ClassNotFoundException e) {
			logger.fatal("Can't load JDBC driver class", e);
		}
	}
}
