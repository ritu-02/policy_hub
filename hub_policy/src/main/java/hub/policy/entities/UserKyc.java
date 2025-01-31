package hub.policy.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="user_kyc")
@NoArgsConstructor
@Getter
@Setter
@ToString

public class UserKyc {
	
	@Id
	@Column(name="kyc_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long kycId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id",nullable=false)
	@NotNull(message="userId cannot be null ")
	private User userId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="document_type",nullable=false)
	private DocumentType documentType;
	
	@Column(name="document_number",nullable=false, length =50)
	@NotNull(message="documentNumber cannot be null ")
	private String documentNumber;
	
	@Column(name="document_url",nullable=false)
	@NotNull(message="kycStatus cannot be null ")
	private String documentUrl;
	
	@Enumerated(EnumType.STRING)
	@Column(name="kyc_status",nullable=false)
	private KYCStatus kycStatus;
	
	@Column(name="uploaded_at")
	@CreationTimestamp
	private LocalDate uploadedAt;
	

}
