package com.vijay.messenger.chatGroup;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vijay.messenger.model.Profile;
import com.vijay.messenger.service.ProfileService;

@Path("/profileResource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	ProfileService service = new ProfileService();
	public ProfileResource() {
	}
	
	@GET
	public List<Profile> getProfiles(){
		return service.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfiles(@PathParam("profileName") String profileName){
		return service.getProfile(profileName);
	}
	
	@POST
	public Profile saveProfile(Profile profile){
		return service.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName,Profile profile){
		return service.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public Profile removeProfile(@PathParam("profileName") String profileName){
		return service.removeProfile(profileName);
	}
	
	
	
}
