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

//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//    public Response sendComment(@PathParam("memberId") int memberId, Comment comment) {
//		Comment newComment = commentService.sendComment(memberId, comment);
//		return Response.status(Status.OK)
//		.header("User:", "admin")
//		.entity(newComment)
//		.build(); 
//    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendComment(@PathParam("memberId") int memberId, Comment comment, @Context UriInfo uriInfo) {
		Comment newComment = commentService.sendComment(memberId, comment);

		String uri = uriInfo.getBaseUriBuilder().path(MemberResource.class).path(MemberResource.class, "getCommentResource").resolveTemplate("memberId", newComment.getMemberId()).path(Long.toString(newComment.getCommentId())).build().toString();
		newComment.addLink(uri, "self");
		
		uri = uriInfo.getBaseUriBuilder().path(MemberResource.class).path(Long.toString(newComment.getMemberId())).build().toString();
		newComment.addLink(uri, "member");
		
		String newId = String.valueOf(newComment.getCommentId());
		URI uriA = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uriA)
		.header("User:", "admin")
		.entity(newComment)
		.build(); 
	}


}
