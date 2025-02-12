package hub.policy.dto;

import hub.policy.entities.DocumentType;
import hub.policy.entities.KYCStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class KycDto {
	private KYCStatus kycStatus;
	private DocumentType documentType;
	private String documentNumber;
	

	public KycDto() {
		super();
	}

	public KycDto(KYCStatus kycStatus, DocumentType documentType, String documentNumber) {
		super();
		this.kycStatus = kycStatus;
		this.documentType = documentType;
		this.documentNumber = documentNumber;
	}

	public KYCStatus getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(KYCStatus kycStatus) {
		this.kycStatus = kycStatus;
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

	@Override
	public String toString() {
		return "KycDto [kycStatus=" + kycStatus + ", documentType=" + documentType + ", documentNumber="
				+ documentNumber + "]";
	}

}
