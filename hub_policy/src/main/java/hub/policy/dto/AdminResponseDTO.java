package hub.policy.dto;

import java.time.LocalDate;

import hub.policy.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AdminResponseDTO {
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private LocalDate dateOfBirth;
	private UserRole userRole;
	
	public AdminResponseDTO(Long userId, String firstName, String lastName, String email, String phoneNumber,
			String address, LocalDate dateOfBirth, UserRole userRole) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.userRole = userRole;
	}
	
	
}
