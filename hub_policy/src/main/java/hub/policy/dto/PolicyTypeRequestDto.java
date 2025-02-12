package hub.policy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import hub.policy.entities.InsuranceType;

public class PolicyTypeRequestDto {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	private InsuranceType insuranceType;
	
	public PolicyTypeRequestDto(String insuranceType) {
		super();
		this.insuranceType = InsuranceType.valueOf(insuranceType.toUpperCase());
	}

	public InsuranceType getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}

	@Override
	public String toString() {
		return "PolicyTypeRequestDto [ insuranceType=" + insuranceType + "]";
	}
	
	
	
}
