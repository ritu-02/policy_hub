package hub.policy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.dto.AddPolicyTypeReqDto;
import hub.policy.dto.PolicyTypeDto;
import hub.policy.service.PolicyTypeService;

@RestController
@RequestMapping("/api/policy-type")
@CrossOrigin(origins = "http://localhost:3000")
public class PolicyTypeController {
   @Autowired
   private PolicyTypeService policyTypeService;
   
   @GetMapping
   public ResponseEntity<?> getAllPolicyDetails(){
	   List<PolicyTypeDto> policyDetails=policyTypeService.getAllPolicyTypes();
	   	 if(policyDetails.isEmpty())
	   		 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	   	 return ResponseEntity.ok(policyDetails);
   }
   
   @GetMapping("/{id}")
   public ResponseEntity<?> getPolicyTypeById(@RequestParam Long id){
	   return ResponseEntity.status(HttpStatus.FOUND).body(policyTypeService.getPolicyTypeById(id)) ;
   }
   
  
   
   
}
