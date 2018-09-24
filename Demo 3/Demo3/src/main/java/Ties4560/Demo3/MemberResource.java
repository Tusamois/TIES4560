package Ties4560.Demo3;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */

@Path("/memberresource")
public class MemberResource {
	MemberService memberService = new MemberService();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Member> getPublications() {
		return memberService.getAllMembers();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Member addMember (Member member){
	return memberService.addMember(member);
	}
	
	@GET
	@Path("/{memberId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Member getMember(@PathParam("memberId") int id) {
		Member member = MemberService.getMember(id);
		return member;
	}

}
