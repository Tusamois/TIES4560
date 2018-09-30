package ties4560.demo4;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Member {
	private String name;
	private int member_id;
	private int birthyear;
	private List<Link> links = new ArrayList<>();
	
	public List<Link> getLinks() {
		return links;
	}

	public Member(String name, int id, int birthyear) {
		this.name = name;
		this.member_id = id;
		this.setBirthyear(birthyear);
	}
	
	public Member(String name, int id) {
		this.name = name;
		this.member_id = id;
		this.setBirthyear(1);
	}
	
	public Member() {
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
		return member_id;
	}
	
	public void setId(int id) {
		this.member_id = id;
	}

	public int getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}
	
	public void addLink(String url, String rel) {
		Link newLink = new Link();
		newLink.setLink(url);
		newLink.setRel(rel);
		links.add(newLink);
	}
}
