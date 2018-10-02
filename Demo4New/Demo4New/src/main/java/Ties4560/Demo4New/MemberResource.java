package Ties4560.Demo4New;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myresource" path)
 */

@Path("/members")
public class MemberResource {
	MemberService memberService = new MemberService();
	@Context
	private SecurityContext securityContext;

	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addMember(Member member) {
//		Member newMemberService = memberService.addMember(member);
//		return Response.status(Status.CREATED)
//		.header("User:", "admin")
//		.entity(newMemberService)
//		.build(); 
//	}
	
	
	// User ja Admin
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	//@RolesAllowed("admin")
	public Response addMember(Member member, @Context UriInfo uriInfo) {
		Member newMemberService = memberService.addMember(member);
		
		String uri = uriInfo.getBaseUriBuilder().path(MemberResource.class).path(Long.toString(member.getId())).build().toString();
		newMemberService.addLink(uri, "self");
		
		uri = uriInfo.getBaseUriBuilder().path(MemberResource.class).path(MemberResource.class, "getCommentResource").resolveTemplate("memberId", member.getId()).build().toString();
		newMemberService.addLink(uri, "comments");
		
		String newId = String.valueOf(newMemberService.getId());
		URI uriA = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uriA)
		.header("User:", "admin")
		.entity(newMemberService)
		.build(); 
	}

	// Guest, User ja  Admin
	@GET
	@Path("/{memberId}")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response getMember(@PathParam("memberId") int memberId) {
		Member newMemberService = MemberService.getMember(memberId);
		return Response.status(Status.FOUND)
		.header("User:", "admin")
		.entity(newMemberService)
		.build();
	}

	//User ja Admin
	@PUT
	@Path("/{memberId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response updateMember(@PathParam("memberId") int memberId, Member member) {
		// member.getId(id);
		Member newMemberService = memberService.updateMember(member, memberId);
		return Response.status(Status.OK)
		.header("User:", "admin")
		.entity(newMemberService)
		.build(); 
	}

	// Admin
	@DELETE
	@Path("/{memberId}")
	@RolesAllowed("admin")
	public Response deleteMember(@PathParam("memberId") int memberId) {
		memberService.removeMember(memberId);
		return Response.status(Status.OK)
		.header("User:", "admin")
		.build(); 
	}

	// Guest, User ja  Admin
	@GET
	//@RolesAllowed("admin")
	public Response getMembers(@QueryParam("birthyear") int birthyear, @QueryParam("start") int start,
			@QueryParam("end") int end) {
		List<Member> newMemberService;
		
		if (birthyear > 0) {
			newMemberService = memberService.getAllMembersForYear(birthyear);
		}
		if (start >= 0 && end > 0) {
			newMemberService = memberService.getAllMembersPaginated(start, end);
		}
		else {
			newMemberService =  memberService.getAllMembers();
		}
		
		return Response.status(Status.OK)
		.header("User:", "admin")
		.entity(newMemberService)
		.build(); 
	}
	
	// User ja Admin
	@Path("/{memberId}/comments")
	@RolesAllowed("admin")
	public CommentResource getCommentResource(@PathParam("memberId") int memberId){
		return new CommentResource(memberId); 
		
		
	}
}
