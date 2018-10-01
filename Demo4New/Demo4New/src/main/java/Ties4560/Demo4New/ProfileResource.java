package Ties4560.Demo4New;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myresource" path)
 */

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	private ProfileService profileService = new ProfileService();
	
	@Context
	private SecurityContext securityContext;

	//ADMIN
	@POST
	public Response addProfile(Profile profile, @Context UriInfo uriInfo) {
		Profile newprofileService = profileService.addProfile(profile);
		String newName = String.valueOf(newprofileService.getName());
		URI uriA = uriInfo.getAbsolutePathBuilder().path(newName).build();
		return Response.created(uriA)
		.header("Profile:", "admin")
		.entity(newprofileService)
		.build(); 
	}

	//ADMIN, USER, GUEST
	@GET
	@Path("/{profileName}")
	@RolesAllowed("admin")
	public Profile getProfile(@PathParam("profileName") String profileName) {
		Profile newprofileService = ProfileService.getProfile(profileName);
		if (!securityContext.isUserInRole("admin")) {
			throw new WebApplicationException("Not authorized", 401);
		}
		return profileService.getProfile(profileName);
	}

	//ADMIN, USER(self)
	@PUT
	@Path("/{profileName}")
	public Response updateprofile(@PathParam("profileName") String profileName, Profile profile) {
		// profile.getId(id);
		Profile newprofileService = profileService.updateProfile(profile, profileName);
		return Response.status(Status.OK)
		.header("Profile:", "admin")
		.entity(newprofileService)
		.build(); 
	}

	//ADMIN
	@DELETE
	@Path("/{profileName}")
	public Response deleteprofile(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
		return Response.status(Status.OK)
		.header("Profile:", "admin")
		.build(); 
	}

	//ADMIN, USER, GUEST
	@GET
	public Response getprofiles() {
		List<Profile> newprofileService;
		newprofileService =  profileService.getAllProfiles();
		
		return Response.status(Status.OK)
		.header("Profile:", "admin")
		.entity(newprofileService)
		.build(); 
	}
}
