package Ties4560.Demo3;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	private CommentService commentService = new CommentService();
	int memberId;
	
	@GET
	public Response getComments(@PathParam("memberId") int memberId){
			List<Comment> newComment = commentService.getAllComments(memberId);
			return Response.status(Status.OK)
					.header("User:", "admin")
					.entity(newComment)
					.build(); 
	}
	
	@GET
	@Path("/{commentId}")
	public Response getComment(@PathParam("memberId") int memberId, @PathParam("commentId") int commentId){
	Comment newComment = commentService.getComment(memberId, commentId);
	return Response.status(Status.OK)
	.header("User:", "admin")
	.entity(newComment)
	.build(); 
	}   
	
    public CommentResource(int memberId) {
		// TODO Auto-generated constructor stub
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public Response sendComment(@PathParam("memberId") int memberId, Comment comment) {
		Comment newComment = commentService.sendComment(memberId, comment);
		return Response.status(Status.OK)
		.header("User:", "admin")
		.entity(newComment)
		.build(); 
    }

}
