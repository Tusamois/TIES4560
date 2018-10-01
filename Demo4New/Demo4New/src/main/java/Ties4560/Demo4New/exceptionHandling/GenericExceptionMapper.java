package Ties4560.Demo4New.exceptionHandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class GenericExceptionMapper implements ExceptionMapper<Throwable>{
	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errMes = new ErrorMessage (ex.getMessage(), 500, "https://www.youtube.com/watch?v=lXMskKTw3Bc");
		return Response.status(Status.NOT_FOUND).entity(errMes).build();
	}
}
