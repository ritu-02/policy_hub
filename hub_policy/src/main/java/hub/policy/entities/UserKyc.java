package hub.policy.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="user_kyc")
public class UserKyc {
	
	@Id
	@Column(name="kyc_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long kycId;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;

	
	@Enumerated(EnumType.STRING)
	@Column(name="document_type",nullable=false)
	private DocumentType documentType;
	
	@Column(name="document_number",nullable=false, length =50,unique=true)
	@NotNull(message="documentNumber cannot be null ")
	private String documentNumber;
	
//	@Column(name="document_url",nullable=false,unique=true)
//	@NotNull(message="kycStatus cannot be null ")
//	private String documentUrl;
	
	@Enumerated(EnumType.STRING)
	@Column(name="kyc_status",nullable=false)
	private KYCStatus kycStatus;
	
	@Column(name="uploaded_at")
	@CreationTimestamp
	private LocalDate uploadedAt;
	
	@PrePersist
	protected void prePersist() {
	    if (this.kycStatus == null) {
	        this.kycStatus = KYCStatus.PENDING;  // Set default value
	    }
	}
	

	public UserKyc() {
		super();
		this.uploadedAt = LocalDate.now();
	}


	public UserKyc(User user, String documentType, String documentNumber) {
		super();
		this.user = user;
		this.documentType = DocumentType.valueOf(documentNumber.toUpperCase());
		this.documentNumber = documentNumber;
		this.kycStatus = KYCStatus.PENDING;
		this.uploadedAt = LocalDate.now();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getKycId() {
		return kycId;
	}

	public void setKycId(Long kycId) {
		this.kycId = kycId;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public KYCStatus getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(KYCStatus kycStatus) {
		this.kycStatus = kycStatus;
	}

	public LocalDate getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(LocalDate uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	@Override
	public String toString() {
		return "UserKyc [user=" + user + ", documentType=" + documentType + ", documentNumber=" + documentNumber
				+ ", kycStatus=" + kycStatus + "]";
	}



	
	

	
	
}
