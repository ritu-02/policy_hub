package hub.policy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.custom_exceptions.BadCredentialsExcption;
import hub.policy.custom_exceptions.UnauthorizedException;
import hub.policy.custom_exceptions.UnauthorizedRoleException;
import hub.policy.dao.UserDao;
import hub.policy.dto.AdminResponseDTO;
import hub.policy.dto.ApiResponse;
import hub.policy.dto.AuthRequest;
import hub.policy.dto.AuthResponse;
import hub.policy.dto.Signup;
import hub.policy.entities.User;
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
    @Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
    
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody @Valid AuthRequest request){
    	String username = request.getEmail();
    	String password = request.getPassword();
    	
    	//Authenticate user
    	 Authentication authentication;
         try {
             authentication = mgr.authenticate(
                 new UsernamePasswordAuthenticationToken(username, password)
             );
         } catch (Exception e) {
             throw new BadCredentialsExcption("Invalid username or password");
         }
    	
         //Retrieve authenticated user
    	User user=userDao.findByEmail(username).orElseThrow(()-> new BadCredentialsExcption("Invalid username or password"));
    	
    		
		//Generate JWT token
		Map<String,Object> response =new HashMap<>();
		response.put("token", utils.generateJwtToken(user.getEmail(), user.getUserRole().name()));
		
		//if the user is an ADMIN, return only necessary fields
    	if(user.getUserRole().name().equals("ADMIN")) {
    		AdminResponseDTO adminDTO = new AdminResponseDTO(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getAddress(), user.getDateOfBirth(), user.getUserRole());
    				
    		response.put("user",adminDTO);		
    	}else {
    		//If not an admin, return the full user object
    		response.put("user",user);
    	}
    	
        return ResponseEntity.status(HttpStatus.OK).body(response);
    	
    }
    
    @PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody @Valid Signup userDetails){
    		return ResponseEntity.status(HttpStatus.CREATED)
 				.body(userService.userRegistration(userDetails));   
	}
    
}
