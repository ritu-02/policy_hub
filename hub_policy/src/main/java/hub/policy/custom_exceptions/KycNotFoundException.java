package hub.policy.custom_exceptions;

public class KycNotFoundException extends RuntimeException {

	public KycNotFoundException(String message) {
		super(message);
	
	}

	
}
