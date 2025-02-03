package hub.policy.custom_exceptions;

public class UsernameNotFoundException extends RuntimeException {

	public UsernameNotFoundException(String message) {
		super(message);
	}
 
}
