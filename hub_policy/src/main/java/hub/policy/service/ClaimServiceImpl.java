package hub.policy.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hub.policy.custom_exceptions.ResourceNotFoundException;
import hub.policy.dao.ClaimDao;
import hub.policy.dao.UserPolicyDao;
import hub.policy.dto.ClaimDto;
import hub.policy.dto.ClaimRequestDto;
import hub.policy.dto.ClaimUpdateReqDto;
import hub.policy.entities.Claim;
import hub.policy.entities.ClaimStatus;
import hub.policy.entities.UserPolicy;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {
	
	@Autowired
	private ClaimDao claimDao;
	@Autowired
	private UserPolicyDao userPolicyDao;
	@Autowired
	private ModelMapper mapper;
	
	
	// Get all claims
	@Override
	public List<ClaimDto> getAllClaims() {
		return claimDao.findAll()
				.stream()
				 .map(claim -> mapper.map(claim, ClaimDto.class))
		            .collect(Collectors.toList());
	}

	// Get claim by ID
    public ClaimDto getClaimById(Long claimId) {
        Claim claim = claimDao.findById(claimId)
            .orElseThrow(() -> new ResourceNotFoundException("Claim not found with ID: " + claimId));
        return mapper.map(claim, ClaimDto.class);
    }

 // Create a new claim
    public ClaimDto createClaim(ClaimRequestDto claimRequestDTO) {
        UserPolicy userPolicy = userPolicyDao.findById(claimRequestDTO.getUserPolicyId())
            .orElseThrow(() -> new ResourceNotFoundException("User Policy not found"));

        Claim claim = new Claim();
        claim.setUserPolicy(userPolicy);
        claim.setClaimAmount(claimRequestDTO.getClaimAmount());
        claim.setClaimStatus(ClaimStatus.PENDING);

        Claim savedClaim = claimDao.save(claim);
        return mapper.map(savedClaim, ClaimDto.class);
    }

	
	 // Update claim amount
    @Override
    public ClaimDto updateClaimAmount(Long claimId, ClaimUpdateReqDto updateClaim) {
        Claim claim = claimDao.findById(claimId)
            .orElseThrow(() -> new ResourceNotFoundException("Claim not found with ID: " + claimId));
        
        claim.setClaimAmount(updateClaim.getClaimAmount());
        claim.setClaimStatus(updateClaim.getClaimStatus());
        
        Claim savedClaim=claimDao.save(claim);
        return mapper.map(savedClaim, ClaimDto.class);
    }

    @Override
    // Delete claim
    public void deleteClaim(Long claimId) {
        if (!claimDao.existsById(claimId)) {
            throw new ResourceNotFoundException("Claim not found with ID: " + claimId);
        }
        claimDao.deleteById(claimId);
    }

	@Override
	public void updateClaimStatus(Long claimId, ClaimStatus status) {
        // Fetch claim from database
        Claim claim = claimDao.findById(claimId)
            .orElseThrow(() -> new ResourceNotFoundException("Claim not found with ID: " + claimId));

        // Ensure claim is in PENDING status before updating
        if (claim.getClaimStatus() != ClaimStatus.PENDING) {
            throw new IllegalStateException("Only PENDING claims can be updated.");
        }

        // Update claim status
        claim.setClaimStatus(status);
        claimDao.save(claim);
    }

   

}
