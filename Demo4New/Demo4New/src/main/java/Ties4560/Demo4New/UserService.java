package Ties4560.Demo4New;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import Ties4560.Demo4New.exceptionHandling.DataNotFoundException;
import Ties4560.Demo4New.exceptionHandling.InvalidIdException;

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
	public static User getUser(int id) {
		User m = null;
		for (User user : list) {
			if(user.getId() == id) {m=user; break;}
		}
		if(m == null) {
			throw new DataNotFoundException("User with id "+ id +" not found");
		}
		return m; 
		
	}
	
	/**
	 * Get one user
	 * @param name
	 * @return
	 */
	public static User getUser(String name) {
		User m = null;
		for (User user : list) {
			if(user.getName().equals(name)) {m=user; break;}
		}
		if(m == null) {
			throw new DataNotFoundException("User with name "+ name +" not found");
		}
		return m; 
		
	}

	/**
	 * Adding user to list
	 * @param user
	 * @return
	 */
	public User addUser(User user) {
		lastId = lastId + 1;
		user.setId(lastId);
		list.add(user);
		return user;
		//TODO Check if users input 
	}

	/**
	 * Update user by ID
	 * @param user
	 * @param id
	 * @return
	 */
	public User updateUser(User user, int id) {
		if(id > lastId) {
			throw new InvalidIdException("User with ID "+ id +" does not exist. (ID too high)");
		}
		User oldUser = getUser(id);
		oldUser.setName(user.getName());
		return oldUser;
		//TODO Birth year update
		
	}

	/**
	 * Delete user by ID
	 * @param id
	 */
	public void removeUser(int id) {
		if(id > lastId) {
			throw new InvalidIdException("User with ID "+ id +" does not exist. (ID too high)");
		}
		User oldUser = getUser(id);
		list.remove(oldUser);		
		// TODO Return some info
		
	}

	
	
	
	public static int getLastIndex() {
		return lastId;
	}

	/**
	 * Checks if username and password are correct
	 * @param userName
	 * @param password
	 * @return
	 */
	public static boolean userCredentialExists(String userName, String password) {
		for (User user : list) {
			if(user.getName().equals(userName)) {
				if(user.getPassword().equals(password)) {return true;}
			}
		}
		return false;
	}
	
}

