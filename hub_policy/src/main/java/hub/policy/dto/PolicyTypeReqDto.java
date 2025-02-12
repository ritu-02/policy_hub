package hub.policy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import hub.policy.entities.InsuranceType;

public class PolicyTypeReqDto {
	@JsonProperty(access = Access.READ_ONLY)
	private Long typeId;
	private InsuranceType insuranceType;
	private String description;
	
	public PolicyTypeReqDto(InsuranceType insuranceType, String description) {
		super();
		this.insuranceType = insuranceType;
		this.description = description;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
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
		return "PolicyTypeReqDto [typeId=" + typeId + ", insuranceType=" + insuranceType + ", description="
				+ description + "]";
	}
	
	
	
}
