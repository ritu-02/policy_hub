package hub.policy.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class PolicyPlanReqDto {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Long policyPlanId;
	private String policyPlanName;
	private String description;
	private double premium;
	private double sumInsured;
	private String term;
	private String eligibility;
	private LocalDateTime updatedAt;
	private PolicyTypeDto policyType;
	
	public PolicyPlanReqDto(String policyPlanName, String description, double premium, double sumInsured,String term,
			String eligibility, PolicyTypeDto policyType) {
		super();
		this.policyPlanName = policyPlanName;
		this.description = description;
		this.premium = premium;
		this.sumInsured = sumInsured;
		this.term = term;
		this.eligibility = eligibility;
		this.policyType = policyType;
		this.updatedAt=LocalDateTime.now();
	}

	public Long getPolicyPlanId() {
		return policyPlanId;
	}

	public void setPolicyPlanId(Long policyPlanId) {
		this.policyPlanId = policyPlanId;
	}

	public String getPolicyPlanName() {
		return policyPlanName;
	}

	public void setPolicyPlanName(String policyPlanName) {
		this.policyPlanName = policyPlanName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public PolicyTypeDto getPolicyType() {
		return policyType;
	}

	public void setPolicyType(PolicyTypeDto policyType) {
		this.policyType = policyType;
	}

	@Override
	public String toString() {
		return "PolicyPlanReqDto [policyPlanId=" + policyPlanId + ", policyPlanName=" + policyPlanName
				+ ", description=" + description + ", premium=" + premium + ", sumInsured=" + sumInsured + ", term="
				+ term + ", eligibility=" + eligibility + ", updatedAt=" + updatedAt + ", policyType=" + policyType
				+ "]";
	}

	
	

}
