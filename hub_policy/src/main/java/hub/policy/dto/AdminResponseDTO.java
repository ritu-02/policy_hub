package hub.policy.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import hub.policy.entities.KYCStatus;
import hub.policy.entities.UserRole;


public class AdminResponseDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	private String city;
	private LocalDate dateOfBirth;
	private UserRole userRole;
	private KYCStatus kycStatus;
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDate createdOn;
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDate updatedOn;
	
	public AdminResponseDTO(Long userId, String firstName, String lastName, String email, String password,
			String phoneNumber, String city, LocalDate dateOfBirth, String userRole, String kycStatus,
			LocalDate createdOn, LocalDate updatedOn) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.dateOfBirth = dateOfBirth;
		this.userRole = UserRole.valueOf(userRole.toUpperCase());
		this.kycStatus = KYCStatus.valueOf(kycStatus.toUpperCase());
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}
	
	

	public AdminResponseDTO() {
		super();
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public KYCStatus getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(KYCStatus kycStatus) {
		this.kycStatus = kycStatus;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "AdminResponseDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", phoneNumber=" + phoneNumber + ", city=" + city
				+ ", dateOfBirth=" + dateOfBirth + ", userRole=" + userRole + ", kycStatus=" + kycStatus
				+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}
	
	
	
	
	
}
