package Ties4560.Demo4New;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

@Path("/")
public class TaskResource {
	
	private TaskService taskService = new TaskService();
	int GroupId;
	
	@GET
	public Response getTasks(@PathParam("GroupId") int groupId){
			List<Task> newTask = taskService.getAllTasks(groupId);
			return Response.status(Status.OK)
					.header("User:", "admin")
					.entity(newTask)
					.build(); 
	}
	
	@GET
	@Path("/{taskId}")
	public Response getTask(@PathParam("GroupId") int groupId, @PathParam("taskId") int taskId){
	Task newTask = taskService.getTask(groupId, taskId);
	return Response.status(Status.OK)
	.header("User:", "admin")
	.entity(newTask)
	.build(); 
	}  
	
    public TaskResource(int groupId) {
		// TODO Auto-generated constructor stub
	}

//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//    public Response sendTask(@PathParam("groupId") int groupId, Task task) {
//		Task newTask = taskService.sendTask(groupId, task);
//		return Response.status(Status.OK)
//		.header("User:", "admin")
//		.entity(newTask)
//		.build(); 
//    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendTask(@PathParam("GroupId") int groupId, Task task, @Context UriInfo uriInfo) {
		Task newTask = taskService.sendTask(groupId, task);

		String uri = uriInfo.getBaseUriBuilder().path(GroupResource.class).path(GroupResource.class, "getTaskResource").resolveTemplate("GroupId", newTask.getGroupId()).path(Long.toString(newTask.getTaskId())).build().toString();
		newTask.addLink(uri, "self");
		
		uri = uriInfo.getBaseUriBuilder().path(GroupResource.class).path(Long.toString(newTask.getGroupId())).build().toString();
		newTask.addLink(uri, "group");
		
		String newId = String.valueOf(newTask.getTaskId());
		URI uriA = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uriA)
		.header("User:", "admin")
		.entity(newTask)
		.build(); 
	}
}
