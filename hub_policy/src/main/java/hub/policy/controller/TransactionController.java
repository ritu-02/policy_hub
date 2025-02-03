package hub.policy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.entities.UserPolicy;
import hub.policy.service.TransactionService;

@RestController
@RequestMapping("/Transaction")

public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	 
	  @GetMapping
	  public List<UserPolicy> getAllUserPolicy(){
		  return transactionService.getAllUserPolicy();
	  }
	  
	  @PostMapping
	  public String addNewUserPolicy(UserPolicy userPolicy) {
		  return transactionService.addNewUserPolicy(userPolicy);
	  }
	 
	  @GetMapping("/{id}")
	  public UserPolicy getSingleUserPolicy(int id){
		  return transactionService.getSingleUserPolicy(id);
	  }
	  
	  @PutMapping("/{id}")
		public String updateUserPolicy(int userPolicyId ,UserPolicy userPolicy) {     	
		    	return transactionService.updateUserPolicy(userPolicyId, userPolicy);
		}
		@DeleteMapping("/{id}")
		public void deleteUserPolicy(int id) {
			transactionService.deleteUserPolicy(id);
		}
}
