package Ties4560.Demo3;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Member {
	private String name;
	
	
	public Member(String name) {
		this.name = name;
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
}
