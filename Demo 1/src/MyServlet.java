

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clientWS.ClientWS;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		ClientWS client = new ClientWS();
		
		String IP = request.getParameter("IP").toString();
		PrintWriter out = response.getWriter();
		
		if(IP.equals("")){
			out.write("error: Please, provide a value!");  
		}
		if(validate(IP)==false) {
			out.write("error: Please, provide an IP address in correct form!");
		}
		else{
			String result = "";
			String city = "";
			result = client.getCountry(IP);
			city = client.getCapital();
			out.write(result + ", " + city);
			//out.write(city);
			
		}
		
		out.flush();
	    out.close();

		
		
	}
	
	/**
	 *  Checks if the ip address is correct
	 * @param ip given ip address
	 * @return true or false
	 */
	public static boolean validate(final String ip) {
	    String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

	    return ip.matches(PATTERN);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
