package ties4560.demo4;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

public class ProfileService {
	private static List<Profile> list = new ArrayList<Profile>(); 
	private static int lastId = 0;
	
	public ProfileService() {};
	
	
	/**
	 * Get all profiles
	 * @return
	 */
	public List<Profile> getAllProfiles() {
		return list;
	}

	/**
	 * Get one profile
	 * @param id
	 * @return
	 */
	public static Profile getProfile(String name) {
		Profile m = null;
		for (Profile profile : list) {
			if(profile.getName().equals(name)) {m=profile; break;}
		}
		return m; 
		
	}

	/**
	 * Adding profile to list
	 * @param profile
	 * @return
	 */
	public Profile addProfile(Profile profile) {
		list.add(profile);
		return profile;
	}

	/**
	 * Update profile by ID
	 * @param profile
	 * @param id
	 * @return
	 */
	public Profile updateProfile(Profile profile, String name) {
		Profile oldProfile = getProfile(name);
		oldProfile.setName(profile.getName());
		return oldProfile;
	}

	/**
	 * Delete profile by ID
	 * @param id
	 */
	public void removeProfile(String name) {
		Profile oldProfile = getProfile(name);
		list.remove(oldProfile);		
		// TODO Return some info
		
	}
	
	public static int getLastIndex() {
		return lastId;
	}
	
}

