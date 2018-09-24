package Ties4560.Demo3;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;

public class CommentResource {
	CommentService commentService = new CommentService();
	int memberId;
	
    public CommentResource(int memberId) {
		// TODO Auto-generated constructor stub
	}

	@POST        
    public void sendMessage(@FormParam("content") String content) {
        /**/
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
