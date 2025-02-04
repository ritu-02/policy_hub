package hub.policy.custom_exceptions;

public class UnauthorizedException extends RuntimeException {

	public UnauthorizedException(String message) {
		super(message);
		
	}
 
}
