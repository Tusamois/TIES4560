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

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	private CommentService commentService = new CommentService();
	int memberId;
	
	@GET
	public List<Comment> getComments(@PathParam("memberId") int memberId){
			return commentService.getAllComments(memberId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("memberId") int memberId, @PathParam("commentId") int commentId){
	return commentService.getComment(memberId, commentId); 
	}   
	
    public CommentResource(int memberId) {
		// TODO Auto-generated constructor stub
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public Comment sendComment(@PathParam("memberId") int memberId, Comment comment) {
		return commentService.sendComment(memberId, comment);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
