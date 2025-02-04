package hub.policy.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.custom_exceptions.AuthenticationException;
import hub.policy.custom_exceptions.UnauthorizedRoleException;
import hub.policy.dao.UserDao;
import hub.policy.dto.AuthRequest;
import hub.policy.dto.AuthResponse;
import hub.policy.dto.Signup;
import hub.policy.entities.User;
import hub.policy.entities.UserRole;
import lombok.NoArgsConstructor;

@Service
@RestController
@NoArgsConstructor
public class UserServiceImpl implements UserService {
   @Autowired
	private UserDao userDao;
   @Autowired
   private ModelMapper mapper;
   @Autowired
   private PasswordEncoder encoder;

	@Override
	public AuthResponse authenticateUser(AuthRequest request) {
		User entity=userDao.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new AuthenticationException("Invalid email or password"));
		return mapper.map(entity, AuthResponse.class);
	}

	@Override
	public String userRegistration(Signup reqDTO) {
		
        User newUser = new User();
        newUser.setFirstName(reqDTO.getFirstName());
        newUser.setLastName(reqDTO.getLastName());
        newUser.setEmail(reqDTO.getEmail());
        newUser.setPassword(encoder.encode(reqDTO.getPassword())); // Encrypt password
        newUser.setPhoneNumber(reqDTO.getPhoneNumber());
        newUser.setAddress(reqDTO.getAddress());
        newUser.setUserRole(reqDTO.getRole()); 
        newUser.setDateOfBirth(reqDTO.getDateOfBirth());
        newUser.setCreatedOn(LocalDate.now());

        userDao.save(newUser);
        return "User registered successfully";
	}
	
	
}
