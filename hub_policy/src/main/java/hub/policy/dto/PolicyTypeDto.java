package hub.policy.dto;

import hub.policy.entities.InsuranceType;

public class PolicyTypeDto {
	private InsuranceType insuranceType;
	private String description;
	
	
	public PolicyTypeDto() {
		super();
	}

	public PolicyTypeDto(InsuranceType insuranceType, String description) {
		super();
		this.insuranceType = insuranceType;
		this.description = description;
	}
	
	public InsuranceType getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PolicyTypeDto [insuranceType=" + insuranceType + ", description=" + description + "]";
	} 

	 
	

}
