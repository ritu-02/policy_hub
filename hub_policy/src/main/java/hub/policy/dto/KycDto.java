package hub.policy.dto;

import hub.policy.entities.KYCStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KycDto {
   private KYCStatus kycStatus;
}
