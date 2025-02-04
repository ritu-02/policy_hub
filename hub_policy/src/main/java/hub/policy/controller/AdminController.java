package hub.policy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.dto.ApiResponse;
import hub.policy.dto.PolicyPlanAddReqDto;
import hub.policy.dto.PolicyPlanReqDto;
import hub.policy.service.InsuranceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired 
	private InsuranceService insuranceService;
	
	 @PostMapping
	  public ResponseEntity<?> addNewPolicyPlan(@RequestBody PolicyPlanAddReqDto addPolicyPlan) {
		  return ResponseEntity.status(HttpStatus.CREATED).body(insuranceService.addNewPolicyPlan(addPolicyPlan));
	  }
	
	 @PutMapping("/{id}")
	  public ResponseEntity<?> updatePolicyPlan(@RequestParam Long id , @RequestBody @Valid PolicyPlanReqDto policyPlan) {     	
	     return ResponseEntity.status(HttpStatus.OK).body(insuranceService.updatePolicyPlan(id, policyPlan)) ;
	  }
	  
	  @DeleteMapping("/{id}")
	  public ResponseEntity<?> deletePolicyPlan(@RequestParam Long id) {
		  return ResponseEntity.ok(new ApiResponse(insuranceService.deletePolicyPlan(id)));
		 
	  }

}
