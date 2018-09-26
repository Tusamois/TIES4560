package Ties4560.Demo3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import Ties4560.Demo3.exceptionHandling.DataNotFoundException;
import Ties4560.Demo3.exceptionHandling.InvalidIdException;

public class GroupService {
	private static List<Group> list = new ArrayList<Group>(); 
	private static int lastId = 0;
	
	public GroupService() {};
	
	
	/**
	 * Get all groups
	 * @return
	 */
	public List<Group> getAllGroups() {
		return list;
	}

	/**
	 * Get one group
	 * @param id
	 * @return
	 */
	public static Group getGroup(int id) {
		Group m = null;
		for (Group group : list) {
			if(group.getId() == id) {m=group; break;}
		}
		if(m == null) {
			throw new DataNotFoundException("Group with id "+ id +" not found");
		}
		return m; 
		
	}

	/**
	 * Adding group to list
	 * @param group
	 * @return
	 */
	public Group addGroup(Group group) {
		lastId = lastId + 1;
		group.setId(lastId);
		list.add(group);
		return group;
		//TODO Check if users input 
	}

	/**
	 * Update group by ID
	 * @param group
	 * @param id
	 * @return
	 */
	public Group updateGroup(Group group, int id) {
		if(id > lastId) {
			throw new InvalidIdException("Group with ID "+ id +" does not exist. (ID too high)");
		}
		Group oldGroup = getGroup(id);
		oldGroup.setName(group.getName());
		return oldGroup;
		//TODO Birth year update
		
	}

	/**
	 * Delete group by ID
	 * @param id
	 */
	public void removeGroup(int id) {
		if(id > lastId) {
			throw new InvalidIdException("Group with ID "+ id +" does not exist. (ID too high)");
		}
		Group oldGroup = getGroup(id);
		list.remove(oldGroup);		
		// TODO Return some info
		
	}

	
	public static int getLastIndex() {
		return lastId;
	}
	
}
