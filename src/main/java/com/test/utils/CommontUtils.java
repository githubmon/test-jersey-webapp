package com.test.utils;

import java.util.Date;

import org.apache.log4j.Logger;

import com.test.business.bean.ServiceResponseBean;

public class CommontUtils {
	
	private static final Logger logger = Logger.getLogger(CommontUtils.class);
	
	public ServiceResponseBean setServiceResponse(  String id, 
										  			String serviceName, 
										  			Object param1, 
										  			String propMsgCode,
										  			String otherMessage,
										  			Boolean isSuccess){
		
		ServiceResponseBean srb = new ServiceResponseBean();	
		String serviceMsg = null;
		
		srb.setCreated(new Date());
		srb.setId(id);
		srb.setServiceName(serviceName);
		
		if(logger.isDebugEnabled()){			
			logger.debug("' Object'=" + param1 + 
						 ", Created:" + srb.getCreated()  + 
						 ", Id:" + srb.getId() + 
						 ", ServiceName:" + srb.getServiceName() + 
						 ", otherMessage:" + srb.getServiceMessage() + 
						 ", Success?:" + srb.getSuccess() );
		}
		
		if(propMsgCode !=null){
			serviceMsg = MessageHandler.getMessage(propMsgCode, new Object [] {param1});	
		}else{
			serviceMsg = param1 + "-" + otherMessage;
		}		
		srb.setServiceMessage(serviceMsg);
		
		srb.setSuccess(isSuccess);
		return srb;
		
	}
	
	public String getServiceMethodName(Throwable t)
	{		 
		StackTraceElement[] elements = t.getStackTrace(); 
		return elements[0].getMethodName();
		
	}
}
