package hub.policy.service;

import hub.policy.dto.AuthRequest;
import hub.policy.dto.AuthResponse;
import hub.policy.dto.Signup;

public interface UserService {
	//login method
  AuthResponse authenticateUser(AuthRequest request);
    //user registration method
  Signup userRegistration(Signup reqDTO);

	
}
