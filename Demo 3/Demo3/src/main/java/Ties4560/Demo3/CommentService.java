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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
