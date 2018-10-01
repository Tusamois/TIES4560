package Ties4560.Demo4New;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("/groups")
public class GroupResource {
	GroupService groupService = new GroupService();

	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addGroup(Group Group) {
//		Group newGroupService = groupService.addGroup(Group);
//		return Response.status(Status.CREATED)
//		.header("User:", "admin")
//		.entity(newGroupService)
//		.build(); 
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGroup(Group Group, @Context UriInfo uriInfo) {
		Group newGroupService = groupService.addGroup(Group);
		
		String uri = uriInfo.getBaseUriBuilder().path(GroupResource.class).path(Long.toString(Group.getId())).build().toString();
		newGroupService.addLink(uri, "self");
		
		uri = uriInfo.getBaseUriBuilder().path(GroupResource.class).path(GroupResource.class, "getTaskResource").resolveTemplate("GroupId", Group.getId()).build().toString();
		newGroupService.addLink(uri, "tasks");
		
		String newId = String.valueOf(newGroupService.getId());
		URI uriA = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uriA)
		.header("User:", "admin")
		.entity(newGroupService)
		.build(); 
	}

	@GET
	@Path("/{GroupId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroup(@PathParam("GroupId") int GroupId) {
		Group newGroupService = GroupService.getGroup(GroupId);
		return Response.status(Status.FOUND)
		.header("User:", "admin")
		.entity(newGroupService)
		.build();
	}

	@PUT
	@Path("/{GroupId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateGroup(@PathParam("GroupId") int GroupId, Group Group) {
		// Group.getId(id);
		Group newGroupService = groupService.updateGroup(Group, GroupId);
		return Response.status(Status.OK)
		.header("User:", "admin")
		.entity(newGroupService)
		.build(); 
	}

	@DELETE
	@Path("/{GroupId}")
	public Response deleteGroup(@PathParam("GroupId") int GroupId) {
		groupService.removeGroup(GroupId);
		return Response.status(Status.OK)
		.header("User:", "admin")
		.build(); 
	}

	@GET
	public Response getGroups() {
		List<Group> newGroupService;
			newGroupService =  groupService.getAllGroups();
		
		return Response.status(Status.OK)
		.header("User:", "admin")
		.entity(newGroupService)
		.build(); 
	}
	
	@Path("/{GroupId}/tasks")
	public TaskResource getTaskResource(@PathParam("GroupId") int GroupId){
		return new TaskResource(GroupId); 
		
		
	}
}
