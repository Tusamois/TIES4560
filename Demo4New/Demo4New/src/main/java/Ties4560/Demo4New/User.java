package Ties4560.Demo4New;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class User implements Principal{
	private String firstName, lastName, login, email, password;
	private List<String> roles = new ArrayList<String>();
	
	public User() {
	}
		
	
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
		this.roles.add("user"); //TODO tämä on testi
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	

	public boolean isMe(String userName, String pw) {
		return (this.login.equals(userName) && this.password.equals(pw));
	}

	public boolean containsMyRole(String[] r) {
		System.out.println("Täällä");
		for(String myR : this.roles) {
			System.out.println("My role:"+myR);
			for(String compR : r) {
				System.out.println("Compare role:"+compR);
				if (myR.equals(compR)) return true;
			}
		}
		return false;
	}

}
