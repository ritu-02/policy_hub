package hub.policy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.dto.KycUploaded;
import hub.policy.service.UserKycStatusService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/userkyc")
public class UserKycController {
	@Autowired
	private UserKycStatusService userKycStatusService;
     
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserStatus(@PathVariable Long userId){
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(userKycStatusService.getUserKycStatus(userId));
	}
	
	public ResponseEntity<?> addKycStatus(@RequestBody KycUploaded kycObj ){
		return null;
	}

}
