package hub.policy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.dto.ClaimDto;
import hub.policy.dto.ClaimRequestDto;
import hub.policy.dto.ClaimUpdateReqDto;
import hub.policy.service.ClaimService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/claim")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ClaimController {
   @Autowired
    private ClaimService claimService;

    // Get all claims
    @GetMapping
    public ResponseEntity<List<ClaimDto>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    // Get claim by ID
    @GetMapping("/{claimId}")
    public ResponseEntity<ClaimDto> getClaimById(@PathVariable Long claimId) {
        return ResponseEntity.ok(claimService.getClaimById(claimId));
    }

    // Create a new claim
    @PostMapping
    public ResponseEntity<ClaimDto> createClaim(@RequestBody ClaimRequestDto claimRequestDto) {
        return ResponseEntity.ok(claimService.createClaim(claimRequestDto));
    }

    // Update claim amount
    @PutMapping("/{claimId}")
    public ResponseEntity<ClaimDto> updateClaimAmount(@PathVariable Long claimId, @RequestBody ClaimUpdateReqDto updateClaim) {
        return ResponseEntity.ok(claimService.updateClaimAmount(claimId, updateClaim));
    }

    // Delete claim
    @DeleteMapping("/{claimId}")
    public ResponseEntity<String> deleteClaim(@PathVariable Long claimId) {
        claimService.deleteClaim(claimId);
        return ResponseEntity.ok("Claim deleted successfully.");
    }
}
