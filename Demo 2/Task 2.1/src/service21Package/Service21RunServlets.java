package service21Package;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * 
 * @author Era2
 *
 */
public class Service21RunServlets {
	public static void main(String[] args) throws Exception {
		ServletHandler sh = new ServletHandler();
		sh.addServletWithMapping(Service21Servlet.class, "/Service21Servlet");
		Server s = new Server(8080);
		s.setHandler(sh);
		s.start();
		s.dumpStdErr();
		s.join();
	}
}
