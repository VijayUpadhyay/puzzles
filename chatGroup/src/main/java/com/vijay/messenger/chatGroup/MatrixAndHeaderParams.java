package com.vijay.messenger.chatGroup;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectionHeader")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class MatrixAndHeaderParams {
	
	//http://localhost:7070/chatGroup/webapi/injectionHeader/annotations;param1=vijay;param2=upadhyay
	
	@GET
	@Path("/annotations")
	public String headerInfo(@MatrixParam("param1") String param1, @MatrixParam("param2") String param2,@HeaderParam("customHeader") String header,
								@CookieParam("JSESSIONID") String sessionId){
		return "Param1: "+param1+" Param2: "+param2+" Header "+header+" sessionId is: "+sessionId;
	}
	// @FormParam -> to receive form data coming from HTML page
	
	@GET
	@Path("/context")
	public String contextData(@Context UriInfo uriInfo,@Context HttpHeaders header){
		String contentId = header.CONTENT_ID;
		String cookies = header.getCookies().toString();
		String path = uriInfo.getAbsolutePath().toString();
		return "Path is: "+path+" contentId: "+contentId+" Cookies: "+cookies;
	}

}
