package com.test.transaction.impl;

import org.apache.log4j.Logger;

import com.test.business.bean.FileTransferBean;
import com.test.transaction.TransactionBo;

public class TransactionBoImpl implements TransactionBo {
	
	private static final Logger logger = Logger.getLogger(TransactionBoImpl.class);

	public void save(FileTransferBean ftb) {
		
		if(logger.isDebugEnabled()){
			logger.debug("INIT - Save method");
		}		
		FileTransferBean fileToSave = ftb;
		if(logger.isDebugEnabled()){
			logger.debug("TODO -- File object = '" + fileToSave + "'");
			logger.debug("END - Save method");
		}
	}

}