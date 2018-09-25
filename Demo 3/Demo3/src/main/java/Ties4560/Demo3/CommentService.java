package Ties4560.Demo3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

public class CommentService {

	private static List<Comment> list = new ArrayList<Comment>(); 
	private static int lastId = 0;
	
	public CommentService() {};
	
	public List<Comment> getAllComments() {
		return list;
	}

	public Comment getComment(int memberId, int commentId) {
		Comment c = null;
		for (Comment comment : list) {
			if(comment.getCommentId() == commentId) {c=comment; break;}
		}
		return c; 
	}

	public List<Comment> getAllComments(int memberId) {
		Comment c = null;
		List<Comment> queryList = new ArrayList<Comment>();
		
		for (Comment comment : list) {
			if(comment.getMemberId() == memberId) {queryList.add(comment);}
		}
		return queryList; 
	}

	public Comment sendComment(int id, Comment comment) {
		lastId = lastId + 1;
		comment.setCommentId(lastId);
		comment.setMemberId(id);
		list.add(comment);
		return comment;
	}

}
