package Ties4560.Demo4New.exceptionHandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidIdExceptionMapper implements ExceptionMapper<InvalidIdException>{
	@Override
	public Response toResponse(InvalidIdException ex) {
		ErrorMessage errMes = new ErrorMessage (ex.getMessage(), 404, "https://www.youtube.com/watch?v=lXMskKTw3Bc");
		return Response.status(Status.NOT_FOUND).entity(errMes).build();
	}
}
