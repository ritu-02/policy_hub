package hub.policy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.custom_exceptions.AuthenticationException;
import hub.policy.custom_exceptions.ResourceNotFoundException;
import hub.policy.dao.KycDao;
import hub.policy.dao.UserDao;
import hub.policy.dto.AdminResponseDTO;
import hub.policy.dto.AuthRequest;
import hub.policy.dto.CustomerResponseDto;
import hub.policy.dto.ForgetPasswordReqDto;
import hub.policy.dto.KycDto;
import hub.policy.dto.Signup;
import hub.policy.dto.UpdateUserReqDto;
import hub.policy.dto.UserResponseDto;
import hub.policy.entities.User;
import hub.policy.entities.UserKyc;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private KycDao kycDao;
	@Autowired
	private ModelMapper mapper;

	public UserServiceImpl() {
		super();
	}

	@Override
	public UserResponseDto authenticateUser(AuthRequest request) {
		User entity = userDao.findByEmailAndPassword(request.getEmail().trim(), request.getPassword().trim())
				.orElseThrow(() -> new AuthenticationException("Invalid email or password"));
		return mapper.map(entity, UserResponseDto.class);
	}

	@Override
	public CustomerResponseDto userRegistration(Signup reqDTO) {

		User newUser = new User();
		newUser.setFirstName(reqDTO.getFirstName());
		newUser.setLastName(reqDTO.getLastName());
		newUser.setEmail(reqDTO.getEmail().trim());
		newUser.setPassword(reqDTO.getPassword());
		newUser.setPhoneNumber(reqDTO.getPhoneNumber());
		newUser.setCity(reqDTO.getCity());
		newUser.setUserRole(reqDTO.getRole());
		newUser.setDateOfBirth(reqDTO.getDateOfBirth());
		newUser.setCreatedOn(LocalDateTime.now());

		User savedUser = userDao.save(newUser);

		return mapper.map(savedUser, CustomerResponseDto.class);
	}

	@Override
	public List<AdminResponseDTO> getAllUsersList() {
		return userDao.findAll().stream().map(user -> mapper.map(user, AdminResponseDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public CustomerResponseDto getUserById(Long userId) {
		User userObj = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
		return mapper.map(userObj, CustomerResponseDto.class);
	}

	@Override
	public CustomerResponseDto updateUserDetailsById(Long userId, UpdateUserReqDto obj) {
		if (userDao.existsById(userId)) {
			User userObj = userDao.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("resource not found"));
			userObj.setFirstName(obj.getFirstName());
			userObj.setLastName(obj.getLastName());
			userObj.setEmail(obj.getEmail());
			userObj.setPassword(obj.getPassword());
			userObj.setPhoneNumber(obj.getPhoneNumber());
			userObj.setCity(obj.getCity());
			User userSaved = userDao.save(userObj);
			return mapper.map(userSaved, CustomerResponseDto.class);
		}
		throw new ResourceNotFoundException("Invalid User id");
	}

	@Override
	public String forgetPassword(ForgetPasswordReqDto obj) {
	    // Fetch user by email
	    User userObj = userDao.findByEmailAndPassword(obj.getEmail().trim(),obj.getOldPassword().trim())
	            .orElseThrow(() -> new ResourceNotFoundException("Invalid Email / Password"));


	    // Validate new password format
	    if (!isValidPassword(obj.getNewPassword().trim())) {
	        return "Password must contain at least one letter, one digit, and one special character";
	    }

	    // Update the password
	    userObj.setPassword(obj.getNewPassword().trim());
	    userDao.save(userObj);  // Persist changes

	    return "Password changed successfully";
	}

	// Utility method for password validation
	private boolean isValidPassword(String password) {
	    String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$";
	    return password.matches(regex);
	}


	@Override
	public KycDto getUserKycStatus(Long userId) {
	     UserKyc kyc= kycDao.findKycByUserId(userId)
	                 .orElseThrow(() -> new ResourceNotFoundException("KYC details not found for user ID: " + userId));
	     return mapper.map(kyc,KycDto.class);
	}


	


}
