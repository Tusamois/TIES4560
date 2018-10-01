package Ties4560.Demo4New;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class User implements Principal{
	private String firstName, lastName, login, email, password;
	
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	private List<String> roles;
	
	public User(String firstName, String lastName, String login, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.email = email;
		this.password = password;
		this.roles = new ArrayList<String>();
	}

	public boolean isMe(String userName, String pw) {
		return (this.login.equals(userName) && this.password.equals(pw));
	}

	public boolean containsMyRole(String[] r) {
		for(String myR : this.roles) {
			for(String compR : r) {
				if (myR.equals(compR)) return true;
			}
		}
		return false;
	}

}
