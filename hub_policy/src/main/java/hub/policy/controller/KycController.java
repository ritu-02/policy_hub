package hub.policy.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.dto.KycRequestDto;
import hub.policy.service.KycServiceImpl;
import hub.policy.service.UserService;

@RestController
@RequestMapping("/policy-hub/user-kyc")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
public class KycController {

	@Autowired
	private KycServiceImpl kycService;
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper mapper;

	@PostMapping("/kyc")
	public ResponseEntity<?> submitKyc( @RequestBody KycRequestDto kycObj) {
	    
	    // Call the service method
	    String response = kycService.submitKyc(kycObj);
	    
	    return ResponseEntity.ok(response);
	}


	@GetMapping("kyc/{id}")
	public ResponseEntity<?> getKycById(@PathVariable Long id) {
		return ResponseEntity.ok(kycService.getKycById(id));
	}

	// Define an API response class
	static class ApiResponse {
		private String message;
		private boolean status;

		public ApiResponse(String message, boolean status) {
			this.message = message;
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public boolean getStatus() {
			return status;
		}
	}
}
