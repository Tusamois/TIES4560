package Ties4560.Client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;

import Ties4560.Demo4New.Member;
import Ties4560.Demo4New.User;

public class Demo4Client {
	public static void main(String[] args) {
		/*Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/Demo4New/webapi/");
		WebTarget membersTarget = baseTarget.path("members");
		WebTarget memberTarget = membersTarget.path("{memberId}");
		Member newmember = new Member("Matti", 1993);
		Response postResponse = membersTarget.request().post(Entity.json(newmember));*/
		/*if (postResponse.getStatus() != 201) {
			System.out.println("Error: member is not created." + postResponse.getStatus());
		} else {
			Member respmember = postResponse.readEntity(Member.class);
			System.out.println(respmember.getName() + " <--- that's your name");
			
			String name = "user";
			String password = "password";
			String authString = name + ":" + password;
			String authStringEnc = Base64.encodeAsString(authString);
			System.out.println("Base64 encoded auth string: " + authStringEnc);
			Response getResponse_ = memberTarget.resolveTemplate("memberId", "1")
			.request(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			
			.header("Authorization", "Basic " + authStringEnc)
			.get();
			Member member = getResponse_.readEntity(Member.class);
			System.out.println("Basic Auth succeeded. Member Name: "+member.getName());
		}*/
		

	}
	
	/**
	 * 
	 * @param login Username
	 * @param password password
	 * @param role role
	 */
	public void registerUser(String login, String password, String role) {
		/*Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/Demo4New/webapi/users");
		//WebTarget usersTarget = baseTarget.path("users");
		//WebTarget memberTarget = membersTarget.path("{memberId}");
		//List<String> roles  = new ArrayList<String>();
		//roles.add(role);
		User newUser = new User("Matti", "Meikalainen", login, "asd@asd.fi", password);
		/*newUser.setLogin(login);
		newUser.setPassword(password);
		newUser.setRoles(roles);
		newUser.setFirstName("asd");
		newUser.setLastName("asdasd");
		newUser.setEmail("asdasdasd");
		System.out.println(Entity.json(newUser));
		Response postResponse = baseTarget.request().post(Entity.json(newUser));
		System.out.println(postResponse.toString());
		if (postResponse.getStatus() != 201) {
			System.out.println("Error: User is not created. " + postResponse.getStatus());
		} else {
			System.out.println("User is created. " + postResponse.getStatus());
		}*/
		
		System.out.println(login + password + role);
		Client client = ClientBuilder.newClient();
		String user = "{ \"login\": \"" + login + "\", \"password\":\""+password + "\" } ";
		Response response = client.target("http://localhost:8080/Demo4New/webapi/users/")
		.request(MediaType.APPLICATION_JSON)
		.post(Entity.json(user));
		System.out.println(response.readEntity(String.class)); 

	}

}
