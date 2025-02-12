package hub.policy.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UpdateUserReqDto {
	@JsonProperty(access = Access.READ_ONLY)
	private Long userId;
	@NotBlank(message="required field")
	private String firstName;
	@NotBlank(message="required field")
	private String lastName;
	@NotBlank(message="Email is required")
	@Email(message="Invalid Email format ")
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
	 private LocalDateTime updatedOn;
	 
	
	public UpdateUserReqDto() {
		super();
	}

	public UpdateUserReqDto(  String firstName,
			String lastName,
			  String email,
			String password,
			 String phoneNumber,
			 String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.city = address;
		this.updatedOn = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "UpdateUserDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", phoneNumber=" + phoneNumber + ", city=" + city + "]";
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
	
	 
	 
}
