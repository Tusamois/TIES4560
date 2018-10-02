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
	UserService userService = new UserService();
	@Context
	private SecurityContext securityContext;

	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addUser(User user) {
//		User newUserService = userService.addUser(user);
//		return Response.status(Status.CREATED)
//		.header("User:", "admin")
//		.entity(newUserService)
//		.build(); 
//	}
	
	
	// User ja Admin
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	//@RolesAllowed("admin")
	public Response addUser(User user, @Context UriInfo uriInfo) {
		User newUserService = userService.addUser(user);
		
		String newId = String.valueOf(newUserService.getId());
		URI uriA = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uriA)
		.header("User:", "admin")
		.entity(newUserService)
		.build(); 
	}

	// Guest, User ja  Admin
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response getUser(@PathParam("userId") int userId) {
		User newUserService = UserService.getUser(userId);
		return Response.status(Status.FOUND)
		.header("User:", "admin")
		.entity(newUserService)
		.build();
	}

	//User ja Admin
	@PUT
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response updateUser(@PathParam("userId") int userId, User user) {
		// user.getId(id);
		User newUserService = userService.updateUser(user, userId);
		return Response.status(Status.OK)
		.header("User:", "admin")
		.entity(newUserService)
		.build(); 
	}

	// Admin
	@DELETE
	@Path("/{userId}")
	@RolesAllowed("admin")
	public Response deleteUser(@PathParam("userId") int userId) {
		userService.removeUser(userId);
		return Response.status(Status.OK)
		.header("User:", "admin")
		.build(); 
	}

	// Guest, User ja  Admin
	@GET
	//@RolesAllowed("admin")
	public Response getUsers() {
		List<User> newUserService =  userService.getAllUsers();
				
		return Response.status(Status.OK)
		.header("User:", "admin")
		.entity(newUserService)
		.build(); 
	}
	
}


