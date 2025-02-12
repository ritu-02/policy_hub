package hub.policy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AuthRequest{
	@NotBlank(message="Email must be supplied !")
	@Email (message = "Enter valid email format")
    private String email;
	@NotNull
    private String Password;
	
	public AuthRequest() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

	
	@Override
	public String toString() {
		return "AuthRequest [email=" + email + "]";
	}
    
	
}
