package hub.policy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import hub.policy.entities.InsuranceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public class AddPolicyTypeReqDto {

    @JsonProperty(access = Access.READ_ONLY)
    private Long typeId;

    @NotNull(message = "Insurance type cannot be null")
    private InsuranceType insuranceType;

    @NotBlank(message = "Description cannot be empty")
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

	public AddPolicyTypeReqDto() {
		super();
	}

	public AddPolicyTypeReqDto( String insuranceType,String description) {
		super();
		this.insuranceType = InsuranceType.valueOf(insuranceType.toUpperCase().trim());
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

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = InsuranceType.valueOf(insuranceType.toUpperCase().trim());
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
    
    
}
