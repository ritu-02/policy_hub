package hub.policy.service;

import java.util.List;

import hub.policy.dto.ClaimDto;
import hub.policy.dto.ClaimRequestDto;
import hub.policy.dto.ClaimUpdateReqDto;
import hub.policy.entities.ClaimStatus;

public interface ClaimService {
	List<ClaimDto> getAllClaims();
	ClaimDto getClaimById(Long claimId);
	ClaimDto createClaim(ClaimRequestDto claimRequestDto);
	ClaimDto updateClaimAmount(Long claimId, ClaimUpdateReqDto updateClaimt);
	void deleteClaim(Long claimId);
	void updateClaimStatus(Long claimId, ClaimStatus approved);
	
}
