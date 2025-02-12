package hub.policy.dto;

import hub.policy.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class ClaimDto {
    
    private Long claimId;
    private Long userPolicyId;
    private double claimAmount;
    private Status claimStatus;
	public ClaimDto(Long userPolicyId, double claimAmount, Status claimStatus) {
		super();
		this.userPolicyId = userPolicyId;
		this.claimAmount = claimAmount;
		this.claimStatus = claimStatus;
	}
	@Override
	public String toString() {
		return "ClaimDto [claimId=" + claimId + ", claimAmount=" + claimAmount + ", claimStatus=" + claimStatus + "]";
	}
    
	
    
   
}
