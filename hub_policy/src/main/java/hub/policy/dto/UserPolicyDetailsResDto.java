package hub.policy.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UserPolicyDetailsResDto {
	
	private Long userPolicyId;
	private Long policyId;
	private String policyName;
	private Long userId;
	private String userEmail;
	private LocalDate startDate;
	private LocalDate endDate;

	public UserPolicyDetailsResDto() {
		super();
	}



	public UserPolicyDetailsResDto(Long policyId, String policyName, Long userId, String userEmail, LocalDate startDate,
			LocalDate endDate) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
		this.userId = userId;
		this.userEmail = userEmail;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public Long getUserPolicyId() {
		return userPolicyId;
	}

	
	public UserPolicyDetailsResDto(Long userPolicyId, Long policyId, String policyName, Long userId, String userEmail,
			LocalDate startDate, LocalDate endDate) {
		super();
		this.userPolicyId = userPolicyId;
		this.policyId = policyId;
		this.policyName = policyName;
		this.userId = userId;
		this.userEmail = userEmail;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public void setUserPolicyId(Long userPolicyId) {
		this.userPolicyId = userPolicyId;
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
		return "UserPolicyDetailsResDto [ policyId=" + policyId + ", policyName="
				+ policyName + ", userId=" + userId + ", userEmail=" + userEmail + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

	

}
