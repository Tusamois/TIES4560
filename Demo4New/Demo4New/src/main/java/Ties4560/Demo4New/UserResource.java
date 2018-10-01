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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;

import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myresource" path)
 */

@Path("/users")
public class UserResource {
	private UserService userService = new UserService();

	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addUser(User user) {
//		User newUserService = userService.addUser(user);
//		return Response.status(Status.CREATED)
//		.header("User:", "admin")
//		.entity(newUserService)
//		.build(); 
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user, @Context UriInfo uriInfo) {
		User newUserService = userService.addUser(user);
		URI uriA = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uriA)
		.header("User:", "admin")
		.entity(newUserService)
		.build(); 
	}

	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		List<User> newUserService;
		newUserService =  userService.getAllUsers();
	
		return Response.status(Status.OK)
		.header("User:", "admin")
		.entity(newUserService)
		.build(); 
	}
		
		
}
