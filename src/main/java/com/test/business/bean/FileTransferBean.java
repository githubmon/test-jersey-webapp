package com.test.business.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "File")
public class FileTransferBean {
	
	private String name;
	private String size;
	private String type;	
	private String pathLocation;
	
	public String getPathLocation() {
		return pathLocation;
	}
	public void setPathLocation(String pathLocation) {
		this.pathLocation = pathLocation;
	}	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}	
}
