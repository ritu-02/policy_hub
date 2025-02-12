package hub.policy.service;

import java.util.List;

import hub.policy.dto.AdminResponseDTO;
import hub.policy.dto.AuthRequest;
import hub.policy.dto.AuthResponse;
import hub.policy.dto.ForgetPasswordReqDto;
import hub.policy.dto.KycDto;
import hub.policy.dto.Signup;
import hub.policy.dto.UpdateUserReqDto;
import hub.policy.dto.UserResponseDto;
import hub.policy.dto.CustomerResponseDto;

public interface UserService {
	//login method
   UserResponseDto authenticateUser(AuthRequest request);
    //user registration method
   CustomerResponseDto userRegistration(Signup reqDTO);
  //to fetch all user details
   List<AdminResponseDTO> getAllUsersList();
   //to get user by id
   CustomerResponseDto getUserById(Long userId);
   //to update user details by id
   CustomerResponseDto updateUserDetailsById(Long userId,UpdateUserReqDto updatedUserObj);
  // to update user email and password
    String forgetPassword(ForgetPasswordReqDto obj);
    Object getUserKycStatus(Long userId);
}
