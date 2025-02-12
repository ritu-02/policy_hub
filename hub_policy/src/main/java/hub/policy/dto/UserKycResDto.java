package hub.policy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import hub.policy.entities.KYCStatus;

public class UserKycResDto {
	@JsonProperty(access = Access.READ_ONLY)
	private Long kycId;
	private KYCStatus kycStatus;
	
	public Long getKycId() {
		return kycId;
	}
	public void setKycId(Long kycId) {
		this.kycId = kycId;
	}
	public KYCStatus getKycStatus() {
		return kycStatus;
	}
	public void setKycStatus(KYCStatus kycStatus) {
		this.kycStatus = kycStatus;
	}
	@Override
	public String toString() {
		return "UserKycResDto [kycId=" + kycId + ", kycStatus=" + kycStatus + "]";
	}
	
	
	
}
