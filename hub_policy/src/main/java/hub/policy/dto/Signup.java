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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Signup {
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
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20}),@Pattern(regexp=\"(?=.*\\\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20}\", message=\"Password must contain at least one digit, one lowercase letter, and one special character (#@$*)")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@NotBlank(message="required field")
	private String phoneNumber;
	@NotBlank(message="required field")
	private String address;
	private UserRole role;
	@NotNull(message="required field")
	private LocalDate dateOfBirth;
	@JsonProperty(access = Access.READ_ONLY)  // Auto-set when user is created
	private LocalDate createdOn;
	 @JsonProperty(access = Access.READ_ONLY) // Auto-set when user is updated
	    private LocalDate updatedOn;
	
	public Signup(String firstName,String lastName,String email,String password,UserRole role,String address,String dob) {
		super();
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.password=password;
		this.role=role;
		this.address=address;
		this.dateOfBirth=LocalDate.parse(dob);
		this.createdOn=LocalDate.now();
	}
	
	
}
