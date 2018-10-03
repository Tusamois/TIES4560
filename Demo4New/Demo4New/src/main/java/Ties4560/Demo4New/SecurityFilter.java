package Ties4560.Demo4New;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import java.lang.reflect.Method;

import org.glassfish.jersey.internal.util.Base64;

import Ties4560.Demo4New.exceptionHandling.ErrorMessage;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	private static final String AUTH_HEADER_KEY = "Authorization";
	private static final String AUTH_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "secured";
	private static final ErrorMessage FORBIDDEN_ErrMESSAGE = new ErrorMessage("Access clocked for all users.", 403, "https://httpstatuses.com/403");
	private static final ErrorMessage UNAUTHORIZED_ErrMESSAGE = new ErrorMessage("Access denied", 401, "https://httpstatuses.com/401");

	@Context private ResourceInfo resourceInfo;
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		UserService US = new UserService();
		User user = null;//new User("erkki", "Esimerkki", "user", "joku", "password");//TODO testi tämäkin
		List<String> authHeader = requestContext.getHeaders().get(AUTH_HEADER_KEY);
		
		//Authentication type:
		String atype; 
		try {
			atype = authHeader.get(0).substring(0, 5);
		}catch(Exception e) {
			atype = "nothing";
		}
		
		
		//BASIC AUTH:
		if (authHeader != null && authHeader.size() > 0 && atype.equals("Basic")) {
			System.out.println("Basic");
			String authToken = authHeader.get(0).replaceFirst(AUTH_HEADER_PREFIX, "");
			String decodedS = Base64.decodeAsString(authToken);
			StringTokenizer tokenizer = new StringTokenizer(decodedS, ":");
			String userName = tokenizer.nextToken();
			String password = tokenizer.nextToken();
			if(UserService.userCredentialExists(userName, password)){
				user = UserService.getUser(userName);
				String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
				requestContext.setSecurityContext(new CustomSecurityContext(user, scheme));
			}
		}
		
		if ((requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) || (requestContext.getMethod().equals("DELETE"))){
			if(user != null) return;
			Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED).entity(UNAUTHORIZED_ErrMESSAGE).build();
			requestContext.abortWith(unauthorizedStatus);
		}
		
		Method resMethod = resourceInfo.getResourceMethod();
		Class<?> resClass = resourceInfo.getResourceClass();
		
		if(resMethod.isAnnotationPresent(PermitAll.class)) {return;}
		if(resMethod.isAnnotationPresent(DenyAll.class)) {
			Response response = Response.status(Response.Status.FORBIDDEN).entity(FORBIDDEN_ErrMESSAGE).build();
			requestContext.abortWith(response);
		}
		if(resMethod.isAnnotationPresent(RolesAllowed.class)) {
			if(rolesMatched(user,resMethod.getAnnotation(RolesAllowed.class))) return;
			Response response = Response.status(Response.Status.UNAUTHORIZED).entity(UNAUTHORIZED_ErrMESSAGE).build();
			requestContext.abortWith(response);
		}
		
		if(resClass.isAnnotationPresent(PermitAll.class)) {return;}
		if(resClass.isAnnotationPresent(DenyAll.class)) {
			Response response = Response.status(Response.Status.FORBIDDEN).entity(FORBIDDEN_ErrMESSAGE).build();
			requestContext.abortWith(response);
		}
		if(resClass.isAnnotationPresent(RolesAllowed.class)) {
			if(rolesMatched(user,resMethod.getAnnotation(RolesAllowed.class))) return;
			Response response = Response.status(Response.Status.UNAUTHORIZED).entity(UNAUTHORIZED_ErrMESSAGE).build();
			requestContext.abortWith(response);
		}
	}
	private boolean rolesMatched(User user, RolesAllowed annotation) {
		String[] roles = annotation.value();
		if(user == null) return false;
		return user.containsMyRole(roles);
	}

}
