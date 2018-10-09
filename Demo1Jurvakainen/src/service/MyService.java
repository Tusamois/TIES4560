package service;

import org.tempuri.Calculator;
import org.tempuri.CalculatorSoap;

import com.cdyne.ws.IP2Geo;
import com.cdyne.ws.IP2GeoSoap;
import com.cdyne.ws.IPInformation;

public class MyService {
	public MyService() {}
	
	/**
	 * This method uses IP2Geo -service. 
	 * It also uses calculateDistance-method that calls Calculator-service. 
	 * @param value IP Address
	 * @return String country + city + distance from Jyväskylä
	 */
	public String getIP(String value) {
		IP2Geo service = new IP2Geo(); //created service object
        IP2GeoSoap serviceSOAP = service.getIP2GeoSoap(); //create SOAP object (a port of the service)
        IPInformation result = serviceSOAP.resolveIP(value, "0");  
        
        //Calculate distance:
        int distance = calculateDistance(result.getLatitude(), result.getLongitude());
        
        String ret = "Distance from " + result.getCity() + " (" + result.getCountry() + ") to Jyvaskyla: " + distance + " km" ;  
        return ret;
        
	}
	
	
	/**
	 * This method calculates distance between Jyväskylä and an another city.
	 * It uses Calculator-service.
	 * @param latitude 
	 * @param longitude
	 * @return distance as km
	 */
	public int calculateDistance(double latitude, double longitude) {
		
		//Coordinates of Jyväskylä:
		double lat1 = 62.24147 * 100000;
		double lon1 = 25.72088 * 100000;
		double lat2 = latitude * 100000;
		double lon2 = longitude * 100000;
		
		//Web service is called here (because it was required to use it):
		Calculator calc = new Calculator();
		CalculatorSoap calcSoap = calc.getCalculatorSoap();
		
		//Double to int, because web service doesn't handle doubles: 
		int lat = calcSoap.subtract((int) lat2, (int) lat1);
		int lon = calcSoap.subtract((int) lon2, (int) lon1);
		
		
		//My calculation:
		double R = 6371000; //earth’s radius as meters
		double latRad1 = Math.toRadians(lat1 / 100000);
		double latRad2 = Math.toRadians(lat2 / 100000);
		double diffLat =  Math.toRadians(((double) lat)/ 100000);
		double diffLon =  Math.toRadians(((double) lon)/ 100000);

		double a = Math.sin(diffLat/2) * Math.sin(diffLat/2) +
		        Math.cos(latRad1) * Math.cos(latRad2) *
		        Math.sin(diffLon/2) * Math.sin(diffLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double result = R * c / 1000;
		 
		return (int) result;
	}
	
}
