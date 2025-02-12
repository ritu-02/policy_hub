package hub.policy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import hub.policy.entities.ClaimStatus;
import hub.policy.entities.Status;

public class ClaimUpdateReqDto {
	@JsonProperty(access=Access.READ_ONLY)
  private Long claimId;
	private double claimAmount;
	@JsonProperty(access = Access.READ_ONLY)
	private ClaimStatus claimStatus;
	
	public ClaimUpdateReqDto() {
		super();
	}

	public ClaimUpdateReqDto(double claimAmount, ClaimStatus claimStatus) {
		super();
		this.claimAmount = claimAmount;
		this.claimStatus = claimStatus;
	}

	public Long getClaimId() {
		return claimId;
	}

	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}

	public double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public ClaimStatus getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(ClaimStatus claimStatus) {
		this.claimStatus = claimStatus;
	}

	@Override
	public String toString() {
		return "ClaimUpdateReqDto [claimId=" + claimId + ", claimAmount=" + claimAmount + ", claimStatus=" + claimStatus
				+ "]";
	}
	
	
}
