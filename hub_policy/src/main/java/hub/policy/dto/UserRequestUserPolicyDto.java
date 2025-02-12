package hub.policy.dto;

public class UserRequestUserPolicyDto {
	private String email;

	public UserRequestUserPolicyDto() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserRequestUserPolicyDto [email=" + email + "]";
	}
	
}
