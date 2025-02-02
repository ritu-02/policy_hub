package hub.policy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.custom_exceptions.AuthenticationException;
import hub.policy.dao.UserDao;
import hub.policy.dto.AuthRequest;
import hub.policy.dto.AuthResponse;
import hub.policy.dto.Signup;
import hub.policy.entities.User;
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
	public Signup userRegistration(Signup reqDTO) {
		User user=mapper.map(reqDTO, User.class);
		user.setPassword(encoder.encode(user.getPassword()));
		return mapper.map(userDao.save(user),Signup.class);
	}
	
	
}
