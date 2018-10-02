package Ties4560.Demo4New;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Hash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//
	public static String getHash(byte[] inputBytes) {
		String hashValue = "";
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(inputBytes);
			byte[] digestedBytes = messageDigest.digest();
			hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashValue;
	}

}
