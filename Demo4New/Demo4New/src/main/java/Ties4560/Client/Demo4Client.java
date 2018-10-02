package Ties4560.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
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
	 * @return 
	 */
	public String registerUser(String login, String password, String role) {
		/*Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/Demo4New/webapi/users");
		//WebTarget usersTarget = baseTarget.path("users");
		//WebTarget memberTarget = membersTarget.path("{memberId}");
		List<String> roles  = new ArrayList<String>();
		roles.add(role);
		User newUser = new User("ss","ss",login,"e",password,roles);
		/*newUser.setLogin(login);
		newUser.setPassword(password);
		newUser.setRoles(roles);
		newUser.setFirstName("asd");
		newUser.setLastName("asdasd");
		newUser.setEmail("asdasdasd");
		System.out.println(Entity.json(newUser));
		Entity<User> entity = Entity.json(newUser);
		Response postResponse = baseTarget.request().post(entity);
		System.out.println(postResponse.toString());
		if (postResponse.getStatus() != 201) {
			System.out.println("Error: User is not created. " + postResponse.getStatus());
		} else {
			System.out.println("User is created. " + postResponse.getStatus());
		}*/
		
		/*System.out.println(login + password + role);
		Client client = ClientBuilder.newClient();
		String user = "user";
		System.out.println(Entity.json(user));
		Response response = client.target("http://localhost:8080/Demo4New/webapi/users/")
		.request(MediaType.APPLICATION_JSON)
		.post(Entity.json(user));
		System.out.println(response.readEntity(String.class));*/ 
		
		
		/*Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/Demo4New/webapi/");
		WebTarget membersTarget = baseTarget.path("members");
		Member newmember = new Member("Matti", 1993,1993);
		System.out.println(Entity.json(newmember));
		Response postResponse = membersTarget.request().post(Entity.json(newmember));
		System.out.println(postResponse.toString());*/
		String queryResult = null;
		String user = "{ \"login\": \"" + login + "\", \"password\":\""+password + "\" } ";
		URL url = null;
		try {
			url = new URL("http://localhost:8080/Demo4New/webapi/users");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Length", "" + user.length());
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			outputStreamWriter.write(user);
			outputStreamWriter.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			queryResult = response.toString();
		} finally {
			connection.disconnect();
			return queryResult;
		}

		
	

	}

}
