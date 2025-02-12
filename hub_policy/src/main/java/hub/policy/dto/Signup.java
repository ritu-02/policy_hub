package hub.policy.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import hub.policy.entities.UserRole;

//@Getter
//@Setter
//@NoArgsConstructor
public class Signup {
	@JsonProperty(access = Access.READ_ONLY)
	private Long userId;
	@NotBlank(message="required field")
	private String firstName;
	@NotBlank(message="required field")
	private String lastName;
	@NotBlank(message="Email is required")
	@Email
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", 
    message = "Enter valid email format")
	private String email;
	@NotNull
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$", 
    message = "Password must contain at least one letter, one digit, and one special character (@$!%*?&#)")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@NotBlank(message="required field")
	private String phoneNumber;
	@NotBlank(message="required field")
	private String city;
	private UserRole role;
	@NotNull(message="required field")
	private LocalDate dateOfBirth;
	@JsonProperty(access = Access.READ_ONLY)  // Auto-set when user is created
	private LocalDateTime createdOn;

	
	public Signup() {
		super();
	}

	public Signup(String firstName,String lastName,String email,String password,String role,String address,String dob) {
		super();
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.password=password;
		this.role = UserRole.valueOf(role.toUpperCase());
		this.city=address;
		this.dateOfBirth=LocalDate.parse(dob);
		this.createdOn=LocalDateTime.now();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(String role) {
	    if (role == null || role.isEmpty()) {
	        throw new IllegalArgumentException("Role cannot be null or empty. Allowed values: CUSTOMER, ADMIN.");
	    }
	    
	    try {
	        this.role = UserRole.valueOf(role.toUpperCase()); 
	    } catch (IllegalArgumentException e) {
	        throw new IllegalArgumentException("Invalid role: " + role + ". Allowed values: CUSTOMER, ADMIN.");
	    }
	}

	
	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Signup [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", phoneNumber=" + phoneNumber + ", city=" + city + ", role=" + role
				+ ", dateOfBirth=" + dateOfBirth + ", createdOn=" + createdOn + "]";
	}


	
	
	
	
	
}
