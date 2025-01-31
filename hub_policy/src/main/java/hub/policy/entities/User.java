package hub.policy.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
@ToString

public class User {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name="first_name",nullable=false, length =50 )
	@NotNull(message="firstName cannot be null ")
	private String firstName;
	
	@Column(name="last_name",nullable=false, length =50)
	@NotNull(message="lastname cannot be null ")
	private String lastname;
	
	@Column(name="email",nullable=false,unique = true, length =50)
	@NotNull(message="email cannot be null ")
	private String email;
	
	@Column(name="password",nullable=false)
	@NotNull(message="password cannot be null ")
	private String password;
	
	@Column(name="phone_number",nullable=false, length =20)
	@NotNull(message="phoneNumber cannot be null ")
	private String phoneNumber;
	
	@Column(name="address",nullable=false)
	@NotNull(message="address cannot be null ")
	private String address;
	
	@Column(name="date_of_birth",nullable=false)
	@NotNull(message="dateOfBirth cannot be null ")
	private LocalDate dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	@Column(name="user_role",nullable=false)
	private UserRole userRole;
	

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('PENDING', 'VERIFIED', 'REJECTED') DEFAULT 'PENDING'")
	private KYCStatus kycStatus;

	@Column(name="created_on")
	@CreationTimestamp
	private LocalDate createdOn;
	
	@Column(name="updated_on")
	@UpdateTimestamp
	private LocalDate updatedOn;
	

}
