package Ties4560.Demo3;

public class Comment {
	
	private String content;
	private int comment_id;

	public Comment(String content, int id) {
		this.content = content;
		this.comment_id = id;
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
	
	public int getId() {
		return comment_id;
	}
	
	public void setId(int id) {
		this.comment_id = id;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
