package hub.policy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.service.InsuranceService;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {
	@Autowired
	private InsuranceService insuranceService;
	 
	  @GetMapping
	  public ResponseEntity<?> getAllPolicyPlan(){
		  return ResponseEntity.ok(insuranceService.getAllPolicyPlan());
	  }
	 
	  @GetMapping("/{id}")
	  public ResponseEntity<?> getSinglePolicyPlan(@RequestParam Long id){
		  return  ResponseEntity.status(HttpStatus.FOUND).body(insuranceService.getSinglePolicyPlan(id));
	  }
	  
	 
}
