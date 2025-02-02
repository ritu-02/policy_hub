package hub.policy.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import hub.policy.entities.DocumentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KycUploaded {
    @JsonProperty(access=Access.READ_ONLY)
	private long kycId;
    @JsonProperty(access=Access.READ_ONLY)
   	private long userId;
    @NotBlank(message="document type must be filled")
    private DocumentType documentType;
    
    
}
