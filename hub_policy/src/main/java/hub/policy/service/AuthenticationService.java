package hub.policy.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import hub.policy.dao.RoleDao;
import hub.policy.dao.UserDao;
import hub.policy.dto.AuthRequest;
import hub.policy.dto.Signup;
import hub.policy.entities.Role;
import hub.policy.entities.User;
import hub.policy.entities.UserRole;

@Service  //  Marks this as a Spring-managed service
@Transactional
public class AuthenticationService {
	@Autowired
	private RoleDao roleDao;
    
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    

    public AuthenticationService(UserDao userDao, PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    // Authenticate method with improved exception handling
    public User authenticate(AuthRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )  
        );

        return userDao.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + request.getEmail()));
    }

    // Sign-up method with default role handling
    public User signUp(Signup input) {
        if (input == null) {
            throw new IllegalArgumentException("Signup request is null");
        }

        if (input.getFirstName() == null || input.getEmail() == null) {
            throw new IllegalArgumentException("First Name and Email are mandatory");
        }

        User user = new User();
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setPhoneNumber(input.getPhoneNumber());
        user.setCity(input.getCity());
        user.setEmail(input.getEmail());
        user.setDateOfBirth(input.getDateOfBirth());

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        // Assign default role CUSTOMER if none provided
        if (input.getRoles() == null || input.getRoles().isEmpty()) {
            System.out.println("User roles are empty or null, assigning default role.");
            Role defaultRole = roleDao.findByRoleName(UserRole.CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            System.out.println("Assigned roles: " + user.getUserRoles());
            user.getUserRoles().add(defaultRole);
            System.out.println("Assigned roles: " + user.getUserRoles());
           
        } else {
        	 Set<Role> roles = input.getRoles()
        			 .stream()
        			 .map(userRole-> roleDao.findByRoleName(userRole).orElseThrow(()-> new RuntimeException("Role not found")))
        			 .collect(Collectors.toSet());
       			 
//        			 
             user.setUserRoles(roles);  // Assign the collected roles to the user
             System.out.println("Assigned roles: " + roles);
        }

        return userDao.save(user);
    }
}
