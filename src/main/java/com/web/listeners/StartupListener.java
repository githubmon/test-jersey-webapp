package com.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class StartupListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		Logger logger = null;
	    String log4jFile = sce.getServletContext().getInitParameter("log4jFileName");
	    DOMConfigurator.configure(sce.getServletContext().getRealPath(log4jFile));
	    logger = LogManager.getLogger(StartupListener.class.getName());
	    
	    logger.info("Loaded: " + log4jFile);		
	}
}
