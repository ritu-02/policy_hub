package hub.policy.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class AuthRequest{
	@NotBlank(message="Email must be supplied !")
	@Email
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", 
    message = "Enter valid email format")
    private String email;
	@NotNull
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$", 
    message = "Password must contain at least one letter, one digit, and one special character (@$!%*?&#)")
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
