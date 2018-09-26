package Ties4560.Demo3.exceptionHandling;

/**
 * 
 * Simple exception handler for an invalid ID
 */
public class InvalidIdException extends RuntimeException{
	private static final long serialVersionUID = 8117153047506463072L;
	public InvalidIdException(String message) {
		super(message);
	}
}
