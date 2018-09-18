package ClientDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 
 * @author Tuomas
 *
 */
public class clientDB {

	public clientDB() {
	}

	private String queryResult = "";

	/**
	 * 
	 * @param str
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public String sendRequest() throws URISyntaxException, IOException {
		String appKey = "jd54omicyiyy7uj"; // get from AppConsole when create the DropBox App
		String redirectURI = "http://localhost:8080/Demo22/"; // any url to where you want to redirect the user
		URI uri = new URI("https://www.dropbox.com/oauth2/authorize");
		StringBuilder requestUri = new StringBuilder(uri.toString());
		requestUri.append("?client_id=");
		requestUri.append(URLEncoder.encode(appKey, "UTF-8"));
		requestUri.append("&response_type=code");
		requestUri.append("&redirect_uri=" + redirectURI.toString());
		queryResult = requestUri.toString();
		return queryResult;
	}

	public String accessToken(String codeStr) throws URISyntaxException, IOException {
		String code = "" + codeStr; // code get from previous step
		String appKey = "jd54omicyiyy7uj"; // get from AppConsole when create the DropBox App
		String appSecret = "jrza3wljcd5jqgr"; // get from AppConsole when create the DropBox App
		String redirectURI = "http://localhost:8080/Demo22/"; // any url to where you want to redirect the user
		StringBuilder tokenUri = new StringBuilder("code=");
		tokenUri.append(URLEncoder.encode(code, "UTF-8"));
		tokenUri.append("&grant_type=");
		tokenUri.append(URLEncoder.encode("authorization_code", "UTF-8"));
		tokenUri.append("&client_id=");
		tokenUri.append(URLEncoder.encode(appKey, "UTF-8"));
		tokenUri.append("&client_secret=");
		tokenUri.append(URLEncoder.encode(appSecret, "UTF-8"));
		tokenUri.append("&redirect_uri=" + redirectURI);
		URL url = new URL("https://api.dropbox.com/oauth2/token");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", "" + tokenUri.toString().length());
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			outputStreamWriter.write(tokenUri.toString());
			outputStreamWriter.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			queryResult = response.toString();
		} finally {
			connection.disconnect();
			return queryResult;
		}
		
	}
	
	/**
	 * 
	 * @param result
	 * @return
	 */
	public static String parse(String result) {
		String a = result.substring(18);
		int b = a.indexOf("\"");
		String c = a.substring(0, b);
		return c;
	}

}
