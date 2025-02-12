package hub.policy.service;



import hub.policy.dto.CustomerResponseDto;
import hub.policy.dto.ForgetPasswordReqDto;
import hub.policy.dto.Signup;
import hub.policy.dto.UpdateUserReqDto;
import hub.policy.entities.User;

public interface CustomerService {
	
   //to get user by id
   CustomerResponseDto getUserById(Long userId);
   //to update user details by id
   CustomerResponseDto updateUserDetailsById(Long userId,UpdateUserReqDto updatedUserObj);
  // to update user email and password
    String forgetPassword(ForgetPasswordReqDto obj);
    //getting user by their kyc
    Object getUserKycStatus(Long userId);
     User getUserByEmailId(String emailId);

	 User addUser(Signup customer);
    
}
