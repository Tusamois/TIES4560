package Ties4560.Demo3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


public class MemberService {
	private static List<Member> list = new ArrayList<Member>(); 
	
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
		try {
			m = list.get(id);
				
		}catch(Exception e){
			m = null;
		}
		return m; 
		
	}

	/**
	 * Adding member to list
	 * @param member
	 * @return
	 */
	public Member addMember(Member member) {
		list.add(member);
		return member;
	}
	
	
}
