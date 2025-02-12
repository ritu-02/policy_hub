package hub.policy.dto;

import hub.policy.entities.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class KycRequestDto {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Document type cannot be null")
    private DocumentType documentType;

    @NotBlank(message = "Document number cannot be blank")
    @Pattern(regexp = "^(\\d{12}|[A-Z0-9]{10}|[A-Z]\\d{7}|[A-Z]{2}\\d{13})$", 
             message = "Invalid document number format")
    private String documentNumber;

    public KycRequestDto() {}

    public KycRequestDto(Long userId, String documentType, String documentNumber) {
        this.userId = userId;
        this.documentType = DocumentType.valueOf(documentType.toUpperCase());
        this.documentNumber = documentNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = DocumentType.valueOf(documentType.toUpperCase());
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        return "KycRequestDto{" +
                "userId=" + userId +
                ", documentType=" + documentType +
                ", documentNumber='" + documentNumber + '\'' +
                '}';
    }
}
