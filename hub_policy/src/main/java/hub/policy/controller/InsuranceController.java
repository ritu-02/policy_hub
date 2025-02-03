package hub.policy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.entities.PolicyPlan;
import hub.policy.service.InsuranceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/insurance")

public class InsuranceController {
	@Autowired
	private InsuranceService insuranceService;
	 
	  @GetMapping
	  public List<PolicyPlan> getAllPolicyPlan(){
		  return insuranceService.getAllPolicyPlan();
	  }
	  
	  @PostMapping
	  public String addNewPolicyPlan(@RequestBody PolicyPlan policyPlan) {
		  return insuranceService.addNewPolicyPlan(policyPlan);
	  }
	 
	  @GetMapping("/{id}")
	  public PolicyPlan getSinglePolicyPlan(@RequestParam Long id){
		  return insuranceService.getSinglePolicyPlan(id);
	  }
	  
	  @PutMapping("/{id}")
		public String updatePolicyPlan(@RequestParam Long id , @RequestBody @Valid PolicyPlan policyPlan) {     	
		    	return insuranceService.updatePolicyPlan(id, policyPlan);
		}
		@DeleteMapping("/{id}")
		public void deletePolicyPlan(@RequestParam Long id) {
			insuranceService.deletePolicyPlan(id);
		}
}
