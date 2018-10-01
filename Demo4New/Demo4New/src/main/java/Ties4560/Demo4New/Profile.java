package Ties4560.Demo4New;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Profile {
	private String name;
	
	public Profile(String name) {
		this.name = name;
	}
	
	public Profile() {
		
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
	
}
