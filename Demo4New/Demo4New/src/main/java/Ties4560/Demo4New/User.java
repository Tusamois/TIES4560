
package Ties4560.Demo4New;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class User implements Principal{
	private String name;
	private int user_id;
	private String password;
	private String role;
	private List<Link> links = new ArrayList<>();
	
	public List<Link> getLinks() {
		return links;
	}

	public User(String name, int id, String password) {
		this.name = name;
		this.user_id = id;
		this.setPassword(password);
	}
	
	public User(String name, int id) {
		this.name = name;
		this.user_id = id;
		this.setPassword("1");
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		for(String r : roles) {
			if(r.equals(this.role)) {return true;}
		}
		return false;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
