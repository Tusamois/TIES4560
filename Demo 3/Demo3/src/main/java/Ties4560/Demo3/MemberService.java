package Ties4560.Demo3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


public class MemberService {
	private static List<Member> list = new ArrayList<Member>(); 
	private static int lastId = 0;
	
	public MemberService() {};
	
	
	/**
	 * Get all members
	 * @return
	 */
	public List<Member> getAllMembers() {
		return list;
	}

	/**
	 * Get one member
	 * @param id
	 * @return
	 */
	public static Member getMember(int id) {
		Member m = null;
		for (Member member : list) {
			if(member.getId() == id) {m=member; break;}
		}
		return m; 
		
	}

	/**
	 * Adding member to list
	 * @param member
	 * @return
	 */
	public Member addMember(Member member) {
		lastId = lastId + 1;
		member.setId(lastId);
		list.add(member);
		return member;
		//TODO Check if users input 
	}

	/**
	 * Update member by ID
	 * @param member
	 * @param id
	 * @return
	 */
	public Member updateMember(Member member, int id) {
		Member oldMember = getMember(id);
		oldMember.setName(member.getName());
		return oldMember;
		// TODO Exception handling (if index is out of bounds)
		
	}

	/**
	 * Delete member by ID
	 * @param id
	 */
	public void removeMember(int id) {
		Member oldMember = getMember(id);
		list.remove(oldMember);		
		// TODO Return some info
		
	}

	/**
	 * Returns all members for given year
	 * @param birthyear
	 * @return
	 */
	public List<Member> getAllMembersForYear(int birthyear) {
		List<Member> queryList = new ArrayList<Member>(); 
		for (Member member : list) {
			if(member.getBirthyear() == birthyear) {
				queryList.add(member);
			}
		}
		return queryList;
	}

	/**
	 * Returns all members for given timeperiod
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Member> getAllMembersPaginated(int start, int end) {
		List<Member> queryList = new ArrayList<Member>(); 
		for (Member member : list) {
			if(member.getBirthyear() >= start && member.getBirthyear() <= end) {
				queryList.add(member);
			}
		}
		return queryList;
	}
	
	
}

