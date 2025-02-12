package hub.policy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.custom_exceptions.BadCredentialsException;
import hub.policy.dao.UserDao;
import hub.policy.dto.AuthRequest;
import hub.policy.dto.AuthResponse;
import hub.policy.dto.CustomerResponseDto;
import hub.policy.dto.Signup;
import hub.policy.dto.UserResponseDto;
import hub.policy.entities.User;
import hub.policy.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allow frontend to call APIs
public class AuthController {
    @Autowired
	private UserService userService;

   
    @PostMapping("/signin")
    public ResponseEntity<?> logIn(@RequestBody @Valid AuthRequest request) {
        try {
            UserResponseDto user = userService.authenticateUser(request);
            return ResponseEntity.ok(user);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    
    
    @PostMapping(value = "/signup", produces = "application/json")
    public ResponseEntity<?> signUp(@RequestBody @Valid Signup userDetails) {
        CustomerResponseDto user = userService.userRegistration(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    	
	
    
}
