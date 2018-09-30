package ties4560.demo4;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class CustomSecurityContext implements SecurityContext{
	private User user;
	private String scheme;
	
	public CustomSecurityContext(User user, String scheme) {
		this.user = user;
		this.scheme = scheme;
	}
	
	@Override
	public Principal getUserPrincipal() {
		return this.user;
	}

	@Override
	public boolean isUserInRole(String role) {
		if(user.getRoles() != null) {
			return user.getRoles().contains(role);
		} return false;
	}

	@Override
	public boolean isSecure() {
		return "https".equals(this.scheme);
	}

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}

}
