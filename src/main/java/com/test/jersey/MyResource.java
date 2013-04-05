
package com.test.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MyResource {
	
	private static final Logger logger = Logger.getLogger(MyResource.class);
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String getIt() {
    	logger.info("INIT - getIt method");
        return "Hi there!";
    }
}
