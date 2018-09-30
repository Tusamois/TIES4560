package ties4560.demo4;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;

public class Demo4Client {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/Demo4/webapi/");
		WebTarget membersTarget = baseTarget.path("members");
		WebTarget memberTarget = membersTarget.path("(memberId)");
		WebTarget commentsTarget = memberTarget.path("comments");
		WebTarget commentTarget = commentsTarget.path("(commentId)");
		WebTarget profilesTarget = baseTarget.path("profiles");
		WebTarget profileTarget = profilesTarget.path("(profileName)");
		
		String name = "user1";
		String password = "password_user1";
		String authString = name+":"+password;
		String authStringEncoded = Base64.encodeAsString(authString);
		System.out.println("Encoded authentication string: " + authStringEncoded);
		
		Response getResponse = profileTarget.resolveTemplate("profileName", "me").request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization", "Basic " + authStringEncoded).get();
		Profile profile = getResponse.readEntity(Profile.class);
		System.out.println("Your profile name is: " + profile.getName());
	}
}
