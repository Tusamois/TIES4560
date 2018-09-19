package ClientDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.*;

/**
 * 
 * @author Tuomas
 *
 */
public class clientDB {

	public clientDB() {
	}

	private static String queryResult = "";

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
	public static String parseToken(String result) {
		/*
		 * String a = result.substring(18); int b = a.indexOf("\""); String c =
		 * a.substring(0, b);
		 */

		JSONObject obj = new JSONObject(result);
		String c = obj.getString("access_token");

		return c;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	public static String parseAccountID(String result) {
		JSONObject obj = new JSONObject(result);
		String c = obj.getString("account_id");

		return c;
	}

	/**
	 * 
	 * @param tokenStr
	 * @param accountIDStr
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static String getAccountInfo(String tokenStr, String accountIDStr) throws URISyntaxException, IOException {
		String access_token = "" + tokenStr;
		String content = "{\"account_id\": \"" + accountIDStr + "\"}";
		URL url = new URL("https://api.dropboxapi.com/2/users/get_account");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + access_token);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Length", "" + content.length());
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			outputStreamWriter.write(content);
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
	 * @param token
	 * @param path
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public String uploadFile(String token, String path) throws URISyntaxException, IOException {
		String access_token = ""+token;
		String sourcePath = ""+path; //required file path on local file system
		Path pathFile = Paths.get(sourcePath);
		byte[] data = Files.readAllBytes(pathFile);
		String content = "{\"path\": \"/Demo2Task22/readme.txt\",\"mode\":\"add\",\"autorename\": true,\"mute\": false,\"strict_conflict\": false}";
		URL url = new URL("https://content.dropboxapi.com/2/files/upload");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Authorization", "Bearer "+access_token);
		connection.setRequestProperty("Content-Type", "application/octet-stream");
		connection.setRequestProperty("Dropbox-API-Arg", "" + content);
		connection.setRequestProperty("Content-Length", String.valueOf(data.length));
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(data);
		outputStream.flush();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
		in.close();
		queryResult = response.toString();
		} finally { 
			connection.disconnect(); 
			return "File uploaded";
		}
		}
	
	
	/**
	 * 
	 * @param tokenStr
	 * @param accountIDStr
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static String getSpaceUsage(String tokenStr, String accountIDStr) throws URISyntaxException, IOException {
		String access_token = "" + tokenStr;
		String content = "{\"account_id\": \"" + accountIDStr + "\"}";
		URL url = new URL("https://api.dropboxapi.com/2/users/get_space_usage");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + access_token);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Length", "" + content.length());
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			outputStreamWriter.write(content);
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

}
