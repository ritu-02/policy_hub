package hub.policy.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.dto.KycRequestDto;
import hub.policy.service.UserKycServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/userkyc")
public class UserKycController {
	@Autowired
	private UserKycServiceImpl userKycService;
     
//	@GetMapping("/{userId}")
//	public ResponseEntity<?> getUserKycStatus(@PathVariable Long userId){
//		return ResponseEntity.status(HttpStatus.FOUND)
//				.body(userKycService.getUserKycStatus(userId));
//	}
	
	@PostMapping("/submit")
	public ResponseEntity<?> submitKyc(@RequestBody KycRequestDto kycObj ){
		try {
			String response=userKycService.verifyKyc(kycObj.getDocumentType(), kycObj.getDocumentNumber(),kycObj.getDocumentFile());
			return ResponseEntity.ok(response);
		}catch (IOException e) {
			return ResponseEntity.badRequest().body("File upload failed");
		}
	}

}
