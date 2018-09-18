package service21Package;

import javax.jws.WebMethod;
import javax.jws.WebService;

import clientWS.ClientWS;

/**
 * 
 * @author Era2
 * Implementation of a Web Service
 */
@WebService
public class Service21Imp implements Service21{

	@Override
	@WebMethod
	/**
	 * Returns the capital of the country from which the given IP address is
	 * @param ip = IP to match
	 * returns Capital
	 * 
	 */
	public String capitalFromIP(String ip) {
		ClientWS.getCountry(ip);
		return ClientWS.getCapital();
	}

}
