package Ties4560.Demo3;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Member {
	private String name;
	private int member_id;
	private int birthyear;
	
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
}
