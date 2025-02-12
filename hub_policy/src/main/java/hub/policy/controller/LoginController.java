package hub.policy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.custom_exceptions.BadCredentialsException;
import hub.policy.dto.UserSignInDetail;
import hub.policy.service.CustomerServiceImpl;



@RestController
@CrossOrigin
@RequestMapping("/policy-hub")
public class LoginController {

	@Autowired
	private CustomerServiceImpl userService;

	@GetMapping("/signIn")
	public ResponseEntity<UserSignInDetail> getLoggedInCustomerDetailsHandler(Authentication auth) {
		try {
			var customer = userService.getUserByEmailId(auth.getName());
			UserSignInDetail signinSuceesData = new UserSignInDetail();
			signinSuceesData.setId(customer.getUserId());
			signinSuceesData.setFirstName(customer.getFirstName());
			signinSuceesData.setLastName(customer.getLastName());
			signinSuceesData.setSigninStatus("Success");

			return new ResponseEntity<>(signinSuceesData, HttpStatus.OK);
		} catch (BadCredentialsException ex) {
			throw new BadCredentialsException(" Invalid Password");
		}

	}
}
