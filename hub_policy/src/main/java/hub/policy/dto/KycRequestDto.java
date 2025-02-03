package hub.policy.dto;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import hub.policy.entities.DocumentType;
import hub.policy.utility.ValidDocumentNumber;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KycRequestDto {
 
    @NotNull(message="document type must be filled")
    private String documentType;
    @ValidDocumentNumber( type = DocumentType.AADHARCARD)
    private String documentNumber;
    @NotNull(message="Document must be uploaded")
    private MultipartFile documentFile;
    
    
    
}
