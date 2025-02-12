package hub.policy.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotNull;


public class PolicyPlanAddReqDto {

	@JsonProperty(access = Access.READ_ONLY)
	private Long policyPlanId;
	 @NotNull(message = "Policy plan name cannot be null")
	private String policyPlanName;
	private String description;
	private double premium;
	private double sumInsured;
	private String term;
	private LocalDateTime createdAt;
	private String eligibility;
	 @NotNull(message = "Policy type is required")
	private Long policyTypeId;

	public PolicyPlanAddReqDto() {
		super();
	}
	
    
	public PolicyPlanAddReqDto(String policyPlanName, String description, double premium, double sumInsured,
			String term, String eligibility, Long policyType) {
		super();
		this.policyPlanName = policyPlanName;
		this.description = description;
		this.premium = premium;
		this.sumInsured = sumInsured;
		this.term = term;
		this.eligibility = eligibility;
		this.policyTypeId= policyType;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	

	public Long getPolicyTypeId() {
		return policyTypeId;
	}


	public void setPolicyTypeId(Long policyTypeId) {
		this.policyTypeId = policyTypeId;
	}


	@Override
	public String toString() {
		return "PolicyPlanAddReqDto [policyPlanId=" + policyPlanId + ", policyPlanName=" + policyPlanName
				+ ", description=" + description + ", premium=" + premium + ", sumInsured=" + sumInsured + ", term="
				+ term + ", createdAt=" + createdAt + ", eligibility=" + eligibility + ", policyTypeId=" + policyTypeId
				+ "]";
	}



	

	

}
