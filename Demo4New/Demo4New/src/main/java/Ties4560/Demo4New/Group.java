package Ties4560.Demo4New;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Group {
	private String name;
	private int group_id;
	private List<Link> links = new ArrayList<>();
	
	public List<Link> getLinks() {
		return links;
	}
	
	public Group(String name, int id) {
		this.name = name;
		this.group_id = id;
	}
	
	public Group() {
	}
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public int getId() {
		return group_id;
	}
	
	public void setId(int id) {
		this.group_id = id;
	}
	
	public void addLink(String url, String rel) {
		Link newLink = new Link();
		newLink.setLink(url);
		newLink.setRel(rel);
		links.add(newLink);
	}
}
