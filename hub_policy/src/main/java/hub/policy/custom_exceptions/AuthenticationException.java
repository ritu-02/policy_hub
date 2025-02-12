package hub.policy.custom_exceptions;

public class AuthenticationException extends RuntimeException{

	public AuthenticationException(String message) {
		super(message);
	}

	 
}
