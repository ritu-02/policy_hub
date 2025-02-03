package hub.policy.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

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
	
	@Column(name="document_number",nullable=false, length =50,unique=true)
	@NotNull(message="documentNumber cannot be null ")
	private String documentNumber;
	
	@Column(name="document_url",nullable=false,unique=true)
	@NotNull(message="kycStatus cannot be null ")
	private String documentUrl;
	
	@Enumerated(EnumType.STRING)
	@Column(name="kyc_status",nullable=false,columnDefinition = "ENUM('PENDING', 'VERIFIED', 'REJECTED') DEFAULT 'PENDING'")
	private KYCStatus kycStatus;
	
	@Column(name="uploaded_at")
	@CreationTimestamp
	private LocalDate uploadedAt;
	

}
