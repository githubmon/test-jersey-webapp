package com.test.business.interfaces;

import java.io.InputStream;

import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.test.business.exceptions.BusinessServiceException;

public interface IJerseyService {
	
	public Response saveDataTransaction() throws BusinessServiceException;
	
	public Response uploadFile(InputStream uploadedInputStream,
			 					FormDataContentDisposition fileDetail,
			 					String device,
			 					String user) throws BusinessServiceException;

}
