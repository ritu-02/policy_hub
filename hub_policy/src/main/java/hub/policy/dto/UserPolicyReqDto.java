package hub.policy.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotNull;

public class UserPolicyReqDto {
	@JsonProperty(access = Access.READ_ONLY)
	private Long userPolicyId;
	private Long userId;
	private Long policyPlanId;
	@NotNull
	private LocalDate startDate;
	@NotNull
	private LocalDate endDate;
	
	
	public UserPolicyReqDto() {
		super();
	}


	public UserPolicyReqDto(Long userId, Long policyPlanId, LocalDate startDate, LocalDate endDate) {
		super();
		this.userId = userId;
		this.policyPlanId = policyPlanId;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public Long getUserPolicyId() {
		return userPolicyId;
	}


	public void setUserPolicyId(Long userPolicyId) {
		this.userPolicyId = userPolicyId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getPolicyPlanId() {
		return policyPlanId;
	}


	public void setPolicyPlanId(Long policyPlanId) {
		this.policyPlanId = policyPlanId;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return "UserPolicyReqDto [userPolicyId=" + userPolicyId + ", userId=" + userId + ", policyPlanId="
				+ policyPlanId + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
	

	

}
