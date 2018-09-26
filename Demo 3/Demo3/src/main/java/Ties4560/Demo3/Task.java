package Ties4560.Demo3;

import java.util.ArrayList;
import java.util.List;

public class Task {
	
	private String description;
	private int task_id;
	private int group_id;
	private List<Link> links = new ArrayList<>();

	public List<Link> getLinks() {
		return links;
	}

	public Task(String description, int task_id, int group_id) {
		this.description = description;
		this.task_id = task_id;
		this.group_id = group_id;
	}
	
	public Task() {
	}
		
	public String getContent() {
		return description;
	}
	
	public void setContent(String description) {
		this.description = description;
	}
	
	public String toString() {
		return description;
	}
	
	public void setTaskId(int id) {
		this.task_id = id;
	}
	
	public void setGroupId(int id) {
		this.group_id = id;
	}

	public int getTaskId() {
		return task_id;
	}
	
	public int getGroupId() {
		return group_id;
	}

	public void addLink(String url, String rel) {
		Link newLink = new Link();
		newLink.setLink(url);
		newLink.setRel(rel);
		links.add(newLink);
	}
}
