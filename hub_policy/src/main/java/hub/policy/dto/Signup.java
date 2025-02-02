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

public class Signup {
	@JsonProperty(access = Access.READ_ONLY)
	private Long userId;
	@NotBlank(message="required field")
	private String firstName;
	@NotBlank(message="required field")
	private String lastname;
	@NotBlank(message="Email is required")
	@Email(message="Invalid Email format ")
	private String email;
	@NotNull
	@Pattern(regexp="((?=.*\\\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@NotBlank(message="required field")
	private String phoneNumber;
	@NotBlank(message="required field")
	private String address;
	private UserRole role;
	@NotBlank(message="required field")
	private LocalDate dateOfBirth;
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDate createdOn;
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime updatedOn;
	
	public Signup(String firstName,String lastName,String email,String password,UserRole role,String address,String dob) {
		super();
		this.firstName=firstName;
		this.lastname=lastName;
		this.email=email;
		this.password=password;
		this.role=role;
		this.address=address;
		this.dateOfBirth=LocalDate.parse(dob);
	}
	
	
}
