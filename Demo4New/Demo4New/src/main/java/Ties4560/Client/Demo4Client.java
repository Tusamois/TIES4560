package Ties4560.Client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;

import Ties4560.Demo4New.Profile;

public class Demo4Client {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/Demo4New/webapi/");
		WebTarget profilesTarget = baseTarget.path("profiles");
		WebTarget profileTarget = profilesTarget.path("{profileName}");
		Profile newProfile = new Profile("me");
		Response postResponse = profilesTarget.request().post(Entity.json(newProfile));
		if(postResponse.getStatus() != 201){System.out.println("Error: Profile is not created." + postResponse.getStatus());
		}else {
		Profile respProfile = postResponse.readEntity(Profile.class);
		System.out.println(respProfile.getName()+" <--- that's your name");
		}
	}
}
