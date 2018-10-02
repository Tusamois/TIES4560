
package Ties4560.Demo4New;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class User implements Principal{
	private String name;
	private int user_id;
	private int birthyear;
	private List<Link> links = new ArrayList<>();
	
	public List<Link> getLinks() {
		return links;
	}

	public User(String name, int id, int birthyear) {
		this.name = name;
		this.user_id = id;
		this.setBirthyear(birthyear);
	}
	
	public User(String name, int id) {
		this.name = name;
		this.user_id = id;
		this.setBirthyear(1);
	}
	
	public User() {
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
		return user_id;
	}
	
	public void setId(int id) {
		this.user_id = id;
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

	public Object getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean containsMyRole(String[] roles) {
		// TODO Auto-generated method stub
		return false;
	}
}
