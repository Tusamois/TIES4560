package Ties4560.Demo3;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */

@Path("/members")
public class MemberResource {
	MemberService memberService = new MemberService();

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Member addMember(Member member) {
		return memberService.addMember(member);
	}

	@GET
	@Path("/{memberId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Member getMember(@PathParam("memberId") int memberId) {
		Member member = MemberService.getMember(memberId);
		return member;
	}

	@PUT
	@Path("/{memberId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Member updateMember(@PathParam("memberId") int memberId, Member member) {
		// member.getId(id);
		return memberService.updateMember(member, memberId);
	}

	@DELETE
	@Path("/{memberId}")
	public void deleteMember(@PathParam("memberId") int memberId) {
		memberService.removeMember(memberId);
	}

	@GET
	public List<Member> getMembers(@QueryParam("birthyear") int birthyear, @QueryParam("start") int start,
			@QueryParam("end") int end) {
		if (birthyear > 0) {
			return memberService.getAllMembersForYear(birthyear);
		}
		if (start >= 0 && end > 0) {
			return memberService.getAllMembersPaginated(start, end);
		}
		return memberService.getAllMembers();
	}
	
    @Path("{memberId}/comments")
    public CommentResource getCommentResourceForRead(@PathParam("memberId") int memberId) {
        return new CommentResource(memberId);
    }
}
