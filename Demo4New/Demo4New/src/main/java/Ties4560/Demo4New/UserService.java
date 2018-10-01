package Ties4560.Demo4New;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

public class UserService {
	private static List<User> list = new ArrayList<User>(); 
	private static int lastId = 0;
	
	public UserService() {};
	
	
	/**
	 * Get all users
	 * @return
	 */
	public List<User> getAllUsers() {
		return list;
	}

	/**
	 * Get one user
	 * @param id
	 * @return
	 */
	public static User getUser(String name) {
		User m = null;
		for (User user : list) {
			if(user.getName().equals(name)) {m=user; break;}
		}
		return m; 
		
	}

	/**
	 * Adding user to list
	 * @param user
	 * @return
	 */
	public User addUser(User user) {
		list.add(user);
		return user;
	}

//	/**
//	 * Update user by ID
//	 * @param user
//	 * @param id
//	 * @return
//	 */
//	public User updateUser(User user, String name) {
//		User oldUser = getUser(name);
//		oldUser.setName(user.getName());
//		return oldUser;
//	}

	/**
	 * Delete user by ID
	 * @param id
	 */
	public void removeUser(String name) {
		User oldUser = getUser(name);
		list.remove(oldUser);		
		// TODO Return some info
		
	}
	
	public static int getLastIndex() {
		return lastId;
	}


	public static boolean userCredentialExists(String userName, String password) {
		for(User u : list) {
			if(u.isMe(userName, password)) return true;
		}
		return false;
	}
	
}

