package hub.policy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.custom_exceptions.AuthenticationException;
import hub.policy.dto.AuthRequest;
import hub.policy.dto.AuthResponse;
import hub.policy.dto.Signup;
import hub.policy.entities.User;
import hub.policy.security.JwtService;
import hub.policy.service.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/policy-hub/auth")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to call APIs
public class AuthenticationController {
	
	private final AuthenticationService authService;
    private final JwtService jwtService;

	public AuthenticationController(AuthenticationService authService, JwtService jwtService) {
		super();
		this.authService = authService;
		this.jwtService = jwtService;
	}


	@PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody  AuthRequest request) {
        try {
            User authenticatedUser = authService.authenticate(request);
            System.out.println(authenticatedUser.getPassword());
            String jwtToken = jwtService.generateToken(authenticatedUser);
            
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(jwtToken);
            authResponse.setExpiresIn(jwtService.getExpirationTime());
            
            return ResponseEntity.ok(authResponse);
            
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password .");
        }catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occured. Please try again later...");
		}
    }
    
    
    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid Signup userDetails) {
       User registeredUser = authService.signUp(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    	
	
    
}
