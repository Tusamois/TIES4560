package service21Package;

import javax.xml.ws.Endpoint;

/**
 * 
 * @author Era2
 * Exports a Web Service
 */
public class Service21Exporter {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/MyServices/Service21", new Service21Imp());
	}
}