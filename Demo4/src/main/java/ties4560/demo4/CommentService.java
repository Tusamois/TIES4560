package ties4560.demo4;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import ties4560.demo4.DataNotFoundException;
import ties4560.demo4.InvalidIdException;

public class CommentService {

	private static List<Comment> list = new ArrayList<Comment>(); 
	private static int lastId = 0;
	
	public CommentService() {};
	
	public List<Comment> getAllComments() {
		return list;
	}

	public Comment getComment(int memberId, int commentId) {
		
		if (commentId > lastId) {
			throw new InvalidIdException("CommentID "+ commentId +" is not valid");
		}
		if (memberId > MemberService.getLastIndex()) {
			throw new InvalidIdException("MemberID "+ memberId +" is not valid");
		}
		
		Comment c = null;
		for (Comment comment : list) {
			if(comment.getCommentId() == commentId) {c=comment; break;}
		}
		if (c == null) {
			throw new DataNotFoundException("Comment from memberID "+ memberId +" with commentID "+ commentId +"not found");
		}
		return c; 
	}

	public List<Comment> getAllComments(int memberId) {
		if (memberId > MemberService.getLastIndex()) {
			throw new InvalidIdException("MemberID "+ memberId +" is not valid");
		}
		Comment c = null;
		List<Comment> queryList = new ArrayList<Comment>();
		
		for (Comment comment : list) {
			if(comment.getMemberId() == memberId) {queryList.add(comment);}
		}
		return queryList; 
	}

	public Comment sendComment(int id, Comment comment) {
		if (id > MemberService.getLastIndex()) {
			throw new InvalidIdException("MemberID "+ id +" is not valid");
		}
		lastId = lastId + 1;
		comment.setCommentId(lastId);
		comment.setMemberId(id);
		list.add(comment);
		return comment;
	}

}
