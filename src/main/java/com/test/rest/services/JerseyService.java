package com.test.rest.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.business.constants.CommonConstants;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.test.business.bean.FileTransferBean;
import com.test.business.bean.ServiceResponseBean;
import com.test.business.exceptions.BusinessServiceException;
import com.test.business.interfaces.IJerseyService;
import com.test.transaction.TransactionBo;
import com.test.utils.CommontUtils;
import com.test.utils.MessageHandler;

@Component
@Path("/service")
public class JerseyService implements IJerseyService {

	@Autowired
	TransactionBo transactionBo;	
	
	private static final Logger logger = Logger.getLogger(JerseyService.class);

	@GET
	@Path("/save")
	@Produces(MediaType.TEXT_XML)	
	public Response saveDataTransaction() {
		
		if(logger.isDebugEnabled()){
			logger.debug("INIT - saveDataTransaction");
		}
		transactionBo.save(new FileTransferBean());
		CommontUtils cut = new CommontUtils();
		
//		fileTransactionBo.saveFile(new FileBean());		
		
		ServiceResponseBean srb = cut.setServiceResponse("999999", this.getClass().getName() + "." + cut.getServiceMethodName(new Throwable()), cut.getServiceMethodName(new Throwable()), CommonConstants.MSG_CODE_SUCCESS_RESPONSE_TRANSACTION_PROP, null, Boolean.TRUE);
		logger.debug("END - 'saveDataTransaction");	
		return Response.ok(srb).build();
	}
	  
	/**
	 * 
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return
	 */
	  @POST    
	  @Path("/uploadFile")
	  @Consumes(MediaType.MULTIPART_FORM_DATA) 
	  @Produces(MediaType.APPLICATION_JSON) 
	  public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			  					 @FormDataParam("file") FormDataContentDisposition fileDetail,
			  					 @FormDataParam("device") String device,
			  					 @FormDataParam("user") String user) {	
		  
		  FileTransferBean ftb    = new FileTransferBean();
		  ServiceResponseBean srb = null;
		  CommontUtils cut          = new CommontUtils();
		  
		  if(logger.isDebugEnabled()){
			  logger.debug("INIT - 'uploadFile' method");
			  logger.debug("invoked from 'Device'="+device); 
			  logger.debug("Invoked by User="+user); 
		  }
		  
		  if(user==null || (user!=null && user.equals("")) ){			  
			  user = CommonConstants.SUBFOLDER_DEFAULT_ID;
			  logger.info("Input Param 'User' received is null or empty. so has been set to DEFAULT ("+user + ")");
		  }
		  		  
		  if(fileDetail == null || 
				  (fileDetail !=null && 
				  	(fileDetail.getFileName() == null || fileDetail.getFileName().equals(""))) ){
			  logger.warn("Input param 'fileDetail' is Null or fileDetail.Name is Null or empty");
			  srb = cut.setServiceResponse("ERROR000000", this.getClass().getName() + "." + cut.getServiceMethodName(new Throwable()), fileDetail, CommonConstants.ERROR_CODE_INPUT_PARAM_NOT_NULL_PROP, null, Boolean.FALSE);
			  throw new BusinessServiceException(srb); 
			  
		  }	
		  else if(uploadedInputStream == null){
			  logger.warn("Input param 'uploadedInputStream' is '"+uploadedInputStream + "'");
			  srb = cut.setServiceResponse("ERROR000000", this.getClass().getName() + "." + cut.getServiceMethodName(new Throwable()), uploadedInputStream,CommonConstants.ERROR_CODE_INPUT_PARAM_NOT_NULL_PROP, null, Boolean.FALSE);
			  throw new BusinessServiceException(srb); 
		  }	
		  else
		  {	  						  
			String uploadedFileLocation = MessageHandler.getMessage(CommonConstants.PATH_TO_UPLOAD_PROP, new Object [] {user});	
			uploadedFileLocation = uploadedFileLocation + fileDetail.getFileName();
			logger.info("File located in '" + uploadedFileLocation + "'");
			try {
				saveToFile(uploadedInputStream, uploadedFileLocation);
			} catch (IOException e) {
				logger.warn("'Exception thrown: " + e.getMessage());
				srb = cut.setServiceResponse("ERROR000000", this.getClass().getName() + "." + cut.getServiceMethodName(new Throwable()), e.getClass().getName(), null, e.getMessage(), Boolean.FALSE);
				throw new BusinessServiceException(srb, e); 
			}									
			ftb.setName(fileDetail.getFileName());
			ftb.setPathLocation(uploadedFileLocation);
			
			//-- Call business transaction:
			transactionBo.save(ftb);		
			
			srb = cut.setServiceResponse("999999", this.getClass().getName() + "." + cut.getServiceMethodName(new Throwable()), cut.getServiceMethodName(new Throwable()), CommonConstants.MSG_CODE_SUCCESS_RESPONSE_TRANSACTION_PROP, null, Boolean.TRUE);
		  }	  
		  if(logger.isDebugEnabled()){
			  logger.debug("END - 'uploadFile' method");
		  }
		  return Response.ok(srb).build();
	  }
	  
	  /**
	   * 
	   * @param uploadedInputStream
	   * @param uploadedFileLocation
	   */
	  private FileOutputStream saveToFile(InputStream uploadedInputStream, String uploadedFileLocation) 
			  throws IOException{
		  
		  FileOutputStream fout = null;
		  if(logger.isDebugEnabled()){
			  logger.debug(" INIT - saveToFile method");
		  }
		  
		
		  int read = 0;
          byte[] bytes = new byte[1024];          
          fout = new FileOutputStream(new File(uploadedFileLocation));
          while ((read = uploadedInputStream.read(bytes)) != -1) {
        	  fout.write(bytes, 0, read);
          }
          fout.flush();
          fout.close();
          
          if(logger.isDebugEnabled()){
			  logger.debug(" END - saveToFile method");
		  }
        
		  return fout;
	  } 
}