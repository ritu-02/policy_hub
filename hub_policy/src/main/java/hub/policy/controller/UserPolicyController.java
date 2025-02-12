package hub.policy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.custom_exceptions.ResourceNotFoundException;
import hub.policy.dto.UserPolicyDetailsResDto;
import hub.policy.dto.UserPolicyReqDto;
import hub.policy.service.UserPolicyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/policy-hub/user-policy")
@Validated
@CrossOrigin
public class UserPolicyController {
    @Autowired
    private  UserPolicyService userPolicyService;

    // Endpoint to purchase a new policy plan
    @PostMapping("/purchase")
    public ResponseEntity<UserPolicyDetailsResDto> purchasePolicy(@Valid @RequestBody UserPolicyReqDto request) {
        try {
            UserPolicyDetailsResDto response = userPolicyService.purchasedPolicyPlan(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint to get all user policies
    @GetMapping("/all")
    public ResponseEntity<List<UserPolicyDetailsResDto>> getAllUserPolicies() {
        List<UserPolicyDetailsResDto> userPolicies = userPolicyService.getAllUserPolicyDetails();
        if (userPolicies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(userPolicies);
    }

    // Endpoint to get policies by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserPolicyByUserId(@PathVariable Long userId) {
        try {
            List<UserPolicyDetailsResDto> userPolicies = userPolicyService.getSingleUserPolicyDetailsByUserId(userId);
            if (userPolicies.isEmpty()) {
                throw new ResourceNotFoundException("No policies found for user ID: " + userId);
            }
            return ResponseEntity.ok(userPolicies);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
