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

import hub.policy.dto.ApiResponse;
import hub.policy.dto.AuthRequest;
import hub.policy.dto.AuthResponse;
import hub.policy.dto.Signup;
import hub.policy.security.JwtUtils;
import hub.policy.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
	private UserService userService;
    @Autowired
    private JwtUtils utils;
    @Autowired
    private AuthenticationManager mgr;
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid AuthRequest request){
    	
    	Authentication verifiedAuth =mgr.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    	return ResponseEntity.ok(new AuthResponse(utils.generateJwtToken(verifiedAuth),"Successful Authentication!!!"));
    }
    
    @PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody @Valid Signup userDetails){
    		return ResponseEntity.status(HttpStatus.CREATED)
 				.body(userService.userRegistration(userDetails));   
	}
    
}
