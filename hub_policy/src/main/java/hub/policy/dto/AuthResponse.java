package hub.policy.dto;

public class AuthResponse {
//	private String jwt;
//	private String message;
	private String role;

	public AuthResponse(String role) {
		super();
		this.role = role;
	}

	@Override
	public String toString() {
		return "AuthResponse [role=" + role + "]";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	public AuthResponse(String jwt, String message) {
//		super();
//		this.jwt = jwt;
//		this.message = message;
//	}
//
//	public AuthResponse() {
//		super();
//	}
//
//	public String getJwt() {
//		return jwt;
//	}
//
//	public void setJwt(String jwt) {
//		this.jwt = jwt;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	@Override
//	public String toString() {
//		return "AuthResponse [jwt=" + jwt + ", message=" + message + "]";
//	}

}