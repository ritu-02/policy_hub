package hub.policy.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude="password")

public class User {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name="first_name",nullable=false, length =50 )
	private String firstName;
	
	@Column(name="last_name",nullable=false, length =50)
	private String lastName;
	
	@Column(name="email",nullable=false,unique = true, length =50)
	private String email;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="phone_number",nullable=false, length =20)
	private String phoneNumber;
	
	@Column(name="address",nullable=false)
	private String address;
	
	@Column(name="date_of_birth",nullable=false)
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
