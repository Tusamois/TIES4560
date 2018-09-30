package ties4560.demo4;

import java.util.ArrayList;
import java.util.List;

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
