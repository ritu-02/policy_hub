package hub.policy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.custom_exceptions.BadCredentialsExcption;
import hub.policy.custom_exceptions.UnauthorizedException;
import hub.policy.custom_exceptions.UnauthorizedRoleException;
import hub.policy.dto.ApiResponse;
import hub.policy.dto.AuthRequest;
import hub.policy.dto.AuthResponse;
import hub.policy.dto.Signup;
import hub.policy.security.JwtUtils;
import hub.policy.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
	private UserService userService;
    @Autowired
    private JwtUtils utils;
    @Autowired
    private AuthenticationManager mgr;
    
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody @Valid AuthRequest request){
    	try {
    	Authentication verifiedAuth = mgr.authenticate(
    			new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    			);
    	String token=utils.generateJwtToken(verifiedAuth);
    	return ResponseEntity.ok(new AuthResponse(token,"Successful Authentication!!!"));
    	}catch (BadCredentialsExcption e) {
			throw new UnauthorizedException("Invalid email or password");
		}
    }
    
    @PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody @Valid Signup userDetails){
    		return ResponseEntity.status(HttpStatus.CREATED)
 				.body(userService.userRegistration(userDetails));   
	}
    
}
