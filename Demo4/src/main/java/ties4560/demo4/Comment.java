package ties4560.demo4;

import java.util.ArrayList;
import java.util.List;

public class Comment {
	
	private String content;
	private int comment_id;
	private int member_id;
	private List<Link> links = new ArrayList<>();

	public List<Link> getLinks() {
		return links;
	}

	public Comment(String content, int comment_id, int member_id) {
		this.content = content;
		this.comment_id = comment_id;
		this.member_id = member_id;
	}
	
	public Comment() {
	}
		
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		return content;
	}
	
	public void setCommentId(int id) {
		this.comment_id = id;
	}
	
	public void setMemberId(int id) {
		this.member_id = id;
	}

	public int getCommentId() {
		return comment_id;
	}
	
	public int getMemberId() {
		return member_id;
	}

	public void addLink(String url, String rel) {
		Link newLink = new Link();
		newLink.setLink(url);
		newLink.setRel(rel);
		links.add(newLink);
	}
}
