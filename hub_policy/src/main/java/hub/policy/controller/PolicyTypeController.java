package hub.policy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hub.policy.dto.AddPolicyTypeReqDto;
import hub.policy.dto.PolicyTypeDto;
import hub.policy.service.PolicyTypeService;

@RestController
@RequestMapping("/policy-hub/policy-type")
@CrossOrigin(origins = "http://localhost:3000") // Specify allowed frontend
public class PolicyTypeController {

    @Autowired
    private PolicyTypeService policyTypeService;

    @GetMapping
    public ResponseEntity<?> getAllPolicyDetails() {
        List<PolicyTypeDto> policyDetails = policyTypeService.getAllPolicyTypes();
        if (policyDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(policyDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPolicyTypeById(@PathVariable Long id) {
    	  return ResponseEntity.status(HttpStatus.FOUND).body(policyTypeService.getPolicyTypeById(id)) ;
    }

    @PostMapping
    public ResponseEntity<?> addPolicyType(@RequestBody AddPolicyTypeReqDto request) {
        policyTypeService.addPolicyType(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
