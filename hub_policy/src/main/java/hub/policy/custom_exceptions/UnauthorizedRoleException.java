package hub.policy.custom_exceptions;

public class UnauthorizedRoleException extends RuntimeException {

	public UnauthorizedRoleException(String message) {
		super(message);
	}
  
}
