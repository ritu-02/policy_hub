package hub.policy.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import hub.policy.custom_exceptions.ResourceNotFoundException;
import hub.policy.custom_exceptions.UserNotFoundException;
import hub.policy.dao.KycDao;
import hub.policy.dao.UserDao;
import hub.policy.dto.CustomerResponseDto;
import hub.policy.dto.ForgetPasswordReqDto;
import hub.policy.dto.KycDto;
import hub.policy.dto.Signup;
import hub.policy.dto.UpdateUserReqDto;
import hub.policy.entities.User;
import hub.policy.entities.UserKyc;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private KycDao kycDao;
	@Autowired
	private ModelMapper mapper;


	public CustomerServiceImpl() {
		super();
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


	@Override
	public User getUserByEmailId(String emailId) {
		return userDao.findByEmail(emailId).orElseThrow(()-> new UserNotFoundException("User not found"));
	}


	@Override
	public User addUser(Signup customer) {
		 if (customer == null)
			    throw new ResourceNotFoundException("customer Can not be Null");
			    Optional<User> findByEmail = userDao.findByEmail(customer.getEmail());
			    if (findByEmail.isPresent()) {
			    System.out.println("inside add user method");
			    throw new RuntimeException("Email alredy Register");
			    }

			    User newCustomer = new User();
			    newCustomer.setEmail(customer.getEmail());
			    newCustomer.setPassword(customer.getPassword());
			    newCustomer.setFirstName(customer.getFirstName());
			    newCustomer.setLastName(customer.getLastName());
			    newCustomer.setPhoneNumber(customer.getPhoneNumber());
			    newCustomer.setCity(customer.getCity());
			    newCustomer.setDateOfBirth(customer.getDateOfBirth());
			   

			    return userDao.save(newCustomer);
	}


	


}
