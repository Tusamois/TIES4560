package clientWS;

import org.oorsprong.websamples.CountryISOCode;
import org.oorsprong.websamples.CountryInfoService;
import org.oorsprong.websamples.CountryInfoServiceSoapType;

import com.cdyne.ws.IP2Geo;
import com.cdyne.ws.IP2GeoSoap;
import com.cdyne.ws.IPInformation;



public class ClientWS {
	
	public ClientWS() {}
	
	public static String apuCountry="";
	
	/*public static void main(String [ ] args)
	{
		String IP = "212.58.249.213";
		String cou = getCountry(IP);
		String cap = getCapital();
		System.out.println(cou);
		System.out.println(cap);
		
	}*/
	
	
	/**
	 * Gives country to a matching IP address
	 * @param IP given IP
	 * @return Country
	 */
	public static String getCountry(String IP) {
		IP2Geo ip2geo = new IP2Geo();
		IP2GeoSoap ipService = ip2geo.getIP2GeoSoap();
		IPInformation geoIP = ipService.resolveIP(IP, "0");
		apuCountry = geoIP.getCountry();
		return geoIP.getCountry();
		
	}
	
	/**
	 * Gives a capital to given country
	 * @param Country
	 * @return
	 */
	public static String getCapital() {
		CountryInfoService CIS = new CountryInfoService();
		CountryInfoServiceSoapType CISService = CIS.getCountryInfoServiceSoap();
		String cISO = CISService.countryISOCode(apuCountry);
		String capital = CISService.capitalCity(cISO);
		return capital;
		
	}
}
