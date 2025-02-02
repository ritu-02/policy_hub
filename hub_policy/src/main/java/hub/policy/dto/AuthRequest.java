package hub.policy.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest{
	@NotBlank(message="Email must be supplied !")
	@Email(message="Invalid Email format ")
    private String email;
	@NotNull
	@Pattern(regexp="((?=.*\\\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})")
	@Length(min=3,max=20,message="Password length must be between 3 and 20")
    private String Password;
    
}
