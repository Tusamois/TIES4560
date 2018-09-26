package Ties4560.Demo3.exceptionHandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class NoValueExceptionMapper implements ExceptionMapper<NoValueException>{
	@Override
	public Response toResponse(NoValueException ex) {
		ErrorMessage errMes = new ErrorMessage (ex.getMessage(), 412, "https://www.youtube.com/watch?v=lXMskKTw3Bc");
		return Response.status(Status.NOT_FOUND).entity(errMes).build();
	}
}
