package hub.policy.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import hub.policy.dao.PolicyPlanDao;
import hub.policy.entities.InsuranceType;
import hub.policy.entities.PolicyPlan;
import hub.policy.entities.PolicyType;

public class PolicyPlanResDto {
     @JsonProperty(access = Access.READ_ONLY)
	private Long policyPlanId;
     private String policyPlanName;
     private String description;
     private double premium;
     private double sumInsured;
     private String term;
     private String eligibility;
     private Long policyTypeId;
     private String policyTypeName;
     
     
	public PolicyPlanResDto() {
		super();
	}

	
	
	public PolicyPlanResDto(String policyPlanName, String description, double premium, double sumInsured, String term,
			String eligibility) {
		super();
		this.policyPlanName = policyPlanName;
		this.description = description;
		this.premium = premium;
		this.sumInsured = sumInsured;
		this.term = term;
		this.eligibility = eligibility;
	}

	@Autowired
	private PolicyPlanDao policyDao;
    

	public PolicyPlanResDto(Long policyPlanId, String policyPlanName, String description, double premium,
			double sumInsured, String term, String eligibility) {
		super();
		this.policyPlanId = policyPlanId;
		this.policyPlanName = policyPlanName;
		this.description = description;
		this.premium = premium;
		this.sumInsured = sumInsured;
		this.term = term;
		this.eligibility = eligibility;

	}

	
    public PolicyPlanResDto(PolicyPlan policyPlan) {
    	 this.policyPlanId = policyPlan.getPolicyPlanId();
         this.policyPlanName = policyPlan.getPolicyPlanName();
         this.description = policyPlan.getDescription();
         this.premium = policyPlan.getPremium();
         this.sumInsured = policyPlan.getSumInsured();
         this.term = policyPlan.getTerm();
         this.eligibility = policyPlan.getEligibility();
         this.policyTypeId = (policyPlan.getPolicyType() != null) ? policyPlan.getPolicyType().getTypeId() : null;
         this.policyTypeName = (policyPlan.getPolicyType() != null) ? policyPlan.getPolicyType().getInsuranceType().name() : null;
     
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
	

	public Long getPolicyTypeId() {
		return policyTypeId;
	}



	public void setPolicyTypeId(Long policyTypeId) {
		this.policyTypeId = policyTypeId;
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



	@Override
	public String toString() {
		return "PolicyPlanResDto [policyPlanId=" + policyPlanId + ", policyPlanName=" + policyPlanName
				+ ", description=" + description + ", premium=" + premium + ", sumInsured=" + sumInsured + ", term="
				+ term + ", eligibility=" + eligibility + ", policyTypeId=" + policyTypeId + ", policyTypeName="
				+ policyTypeName + ", policyDao=" + policyDao + "]";
	}











	
	
	



}
