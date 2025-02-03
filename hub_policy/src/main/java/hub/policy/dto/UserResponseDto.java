package hub.policy.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import hub.policy.entities.KYCStatus;
import hub.policy.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
  
	private Long userId;
	private String firstName;
	private String lastname;
	private String email;
	private String password;
	private String phoneNumber;
	private String address;
	private LocalDate dateOfBirth;
	private UserRole userRole;
	private KYCStatus kycStatus;
	private LocalDate createdOn;
	private LocalDate updatedOn;

}
