package Ties4560.Demo4New.exceptionHandling;

/**
 * 
 * Simple exception handler to prevent empty values
 */
public class NoValueException extends RuntimeException{
	private static final long serialVersionUID = 6092121734302992405L;
	public NoValueException(String message) {
		super(message);
	}
}
