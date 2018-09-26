package Ties4560.Demo3.exceptionHandling;

/**
 * 
 * Simple exception handler for data not found
 */
public class DataNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 6908008143288924942L;
	public DataNotFoundException(String message) {
		super(message);
	}
}
