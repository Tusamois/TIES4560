package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClientDB.clientDB;



/**
 * Servlet implementation class MyConvertor_Servlet
 */
@WebServlet("/MyDBox")
public class MyDBox extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String token = "";
	private static String id ="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyDBox() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		//doPost(request, response);
		clientDB client = new clientDB();
		String result;
		String account;
		String code = request.getParameter("code").toString();
		try {
			result = client.accessToken(code);
			token = clientDB.parseToken(result);
			id = clientDB.parseAccountID(result);
			account = clientDB.getAccountInfo(token, id);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="virhe";
			token = "virhe";
			account = "virhe";
		}
		
		PrintWriter out = response.getWriter();
		out.write(account); 				
		out.flush();
	    out.close();
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		//NumberConvertor client = new NumberConvertor();
		String type = request.getParameter("type").toString();
		clientDB client = new clientDB();
		String result="";
		if(type.equals("file")) {
			String path = request.getParameter("path").toString();
			try {
				result = client.uploadFile(token, path);
				//result = "terve";
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "virhe";
			}
		}
		else if(type.equals("storage")){
			try {
				result = client.getSpaceUsage(token);
				//result = "terve";
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "virhe";
			}
		}
		else {
		
		
		
		//String value = request.getParameter("code").toString();
				
		try {
			result = client.sendRequest();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "virhe";
		}
		}
		
		
		//String value = request.getParameter("value").toString();
		//String type = request.getParameter("type").toString();
		PrintWriter out = response.getWriter();
		
		out.write(result); 				
		out.flush();
	    out.close();
	    
	}

}
