package hub.policy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hub.policy.dto.ApiResponse;
import hub.policy.dto.PolicyTypeRequestDto;
import hub.policy.service.PolicyPlanService;

@RestController
@RequestMapping("/api/policy-plan")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
public class PolicyPlanController {
    
    @Autowired
    private PolicyPlanService policyPlanService;

    @GetMapping
    public ResponseEntity<?> getAllPolicyPlan() {
        return ResponseEntity.ok(new ApiResponse("All policy plans retrieved", policyPlanService.getAllPolicyPlan()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSinglePolicyPlan(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse("Policy plan retrieved", policyPlanService.getSinglePolicyPlan(id)));
    }

    @GetMapping("/plans-by-type")
    public ResponseEntity<?> getAllPolicyPlanByType(@RequestParam String type) {
        return ResponseEntity.ok(new ApiResponse("Policy plans retrieved by type", policyPlanService.getPolicyPlanByType(type)));
    }

  
}
