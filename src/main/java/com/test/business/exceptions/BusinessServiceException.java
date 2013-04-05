package com.test.business.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.sun.jersey.api.Responses;
import com.test.business.bean.ServiceResponseBean;

public class BusinessServiceException extends WebApplicationException {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(BusinessServiceException.class);
	
	/**
	 * Default constructor
	 */
	public BusinessServiceException() {		
		 super(Responses.notFound().build());
	}
	
	public BusinessServiceException(ServiceResponseBean srb) {		
		super(Response.status(500).entity(srb).type(MediaType.APPLICATION_JSON).build());
		logger.error("Business Exception:" + srb.getServiceName() + "\n\t" + srb.getServiceMessage());				
	}
	
	public BusinessServiceException(ServiceResponseBean srb, Exception e) {		
		
		super(Response.status(500).entity(srb).type(MediaType.APPLICATION_JSON).build());
	    
		StringWriter stack = new StringWriter();
	    e.printStackTrace(new PrintWriter(stack));	    
		logger.error("JVM Exception:" + e.getMessage() + "\n\t Stacktrace:" + stack.toString());				
	}

}
