package hub.policy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import hub.policy.dto.AddPolicyTypeReqDto;
import hub.policy.dto.AdminResponseDTO;
import hub.policy.dto.ApiResponse;
import hub.policy.dto.PolicyPlanAddReqDto;
import hub.policy.dto.PolicyPlanReqDto;
import hub.policy.dto.UserKycResDto;
import hub.policy.entities.ClaimStatus;
import hub.policy.service.ClaimService;
import hub.policy.service.KycService;
import hub.policy.service.PolicyPlanService;
import hub.policy.service.PolicyTypeService;
import hub.policy.service.UserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PolicyPlanService policyPlanService;
    
    @Autowired
    private PolicyTypeService policyTypeService;
    
    @Autowired
    private KycService kycService;
    
    @Autowired
    private ClaimService claimService;

    @GetMapping("/user")
    public ResponseEntity<?> getAllUserDetails() {
        List<AdminResponseDTO> usersDetails = userService.getAllUsersList();
        return ResponseEntity.ok(new ApiResponse(usersDetails.isEmpty() ? "No users found" : "Users retrieved successfully", usersDetails));
    }

    @PostMapping("/add-policy-plan")
    public ResponseEntity<?> addNewPolicyPlan(@RequestBody @Valid PolicyPlanAddReqDto addPolicyPlan) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Policy plan added successfully", policyPlanService.addNewPolicyPlan(addPolicyPlan)));
    }

    @PutMapping("/update-policy-plan/{id}")
    public ResponseEntity<?> updatePolicyPlan(@PathVariable Long id, @RequestBody @Valid PolicyPlanReqDto policyPlan) {
        return ResponseEntity.ok(new ApiResponse("Policy plan updated successfully", policyPlanService.updatePolicyPlan(id, policyPlan)));
    }

    @DeleteMapping("/policy-plan/{id}")
    public ResponseEntity<?> deletePolicyPlan(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse(policyPlanService.deletePolicyPlan(id), id));
    }

    @PostMapping("/policy-type")
    public ResponseEntity<?> addPolicyType(@RequestBody @Valid AddPolicyTypeReqDto obj) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Policy type added successfully", policyTypeService.addPolicyType(obj)));
    }

    @PutMapping("/policy-type/{id}")
    public ResponseEntity<?> updatePolicyTypeDetails(@PathVariable Long id, @RequestBody @Valid AddPolicyTypeReqDto obj) {
        return ResponseEntity.ok(new ApiResponse("Policy type updated successfully", policyTypeService.updatePolicyTypeById(id, obj)));
    }

    @DeleteMapping("/policy-type/{id}")
    public ResponseEntity<?> deletePolicyTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse("Policy type deleted successfully", policyTypeService.deletePolicyTypeById(id)));
    }

    @GetMapping("/approve-kyc/{kycId}/{userId}")
    public ResponseEntity<?> approveKyc(@PathVariable Long kycId, @PathVariable Long userId) {
        return ResponseEntity.ok(new ApiResponse("KYC approved successfully", kycService.approveKyc(kycId, userId)));
    }

    @GetMapping("/kyc")
    public ResponseEntity<ApiResponse> getAllKyc() {
        List<UserKycResDto> kycRecords = kycService.getAllKyc();
        return ResponseEntity.ok(new ApiResponse(kycRecords.isEmpty() ? "No KYC records found" : "KYC records retrieved successfully", kycRecords));
    }
    
    // Admin API to approve a claim
    @PutMapping("/{claimId}/approve")
    public ResponseEntity<String> approveClaim(@PathVariable Long claimId) {
        claimService.updateClaimStatus(claimId, ClaimStatus.APPROVED);
        return ResponseEntity.ok("Claim Approved Successfully.");
    }
    
    // Admin API to reject a claim
    @PutMapping("/{claimId}/reject")
    public ResponseEntity<String> rejectClaim(@PathVariable Long claimId) {
        claimService.updateClaimStatus(claimId, ClaimStatus.REJECTED);
        return ResponseEntity.ok("Claim Rejected Successfully.");
    }
    
}
