package hub.policy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hub.policy.custom_exceptions.ResourceNotFoundException;
import hub.policy.dao.PolicyPlanDao;
import hub.policy.dao.UserDao;
import hub.policy.dao.UserPolicyDao;
import hub.policy.dto.UserPolicyDetailsResDto;
import hub.policy.dto.UserPolicyReqDto;
import hub.policy.entities.PolicyPlan;
import hub.policy.entities.User;
import hub.policy.entities.UserPolicy;

@Service
@Transactional
public class UserPolicyServiceImpl implements UserPolicyService {
	
	@Autowired
	private UserPolicyDao userPolicyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PolicyPlanDao policyPlanDao;
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public UserPolicyDetailsResDto purchasedPolicyPlan( UserPolicyReqDto obj) {
	    // Fetch user from the request DTO
	    User user = userDao.findById(obj.getUserId())
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + obj.getUserId()));

	    // Fetch policy plan from the request DTO
	    PolicyPlan policyPlan = policyPlanDao.findById(obj.getPolicyPlanId())
	            .orElseThrow(() -> new ResourceNotFoundException("Policy Plan not found with ID: " + obj.getPolicyPlanId()));

	    // Create and populate the UserPolicy entity
	    UserPolicy userPolicy = new UserPolicy();
	    userPolicy.setUser(user);
	    userPolicy.setPolicyPlan(policyPlan);
	    userPolicy.setStartDate(obj.getStartDate()); // Set current date as purchase date
	    userPolicy.setStatus("ACTIVE"); // Assuming the default status
	    userPolicy.setEndDate(obj.getEndDate());

	    // Save the UserPolicy to the database
	    UserPolicy savedUserPolicy = userPolicyDao.save(userPolicy);

	    // Convert and return response DTO
	    return mapper.map(obj, UserPolicyDetailsResDto.class);
	}


	@Override
	public List<UserPolicyDetailsResDto> getAllUserPolicyDetails() {
	    List<UserPolicyDetailsResDto> userPolicies = userPolicyDao.getAllUserPolicies();
	    
	    if (userPolicies.isEmpty()) {
	        throw new ResourceNotFoundException("No user policies found!");
	    }

	    return userPolicies.stream()
	            .map(userPolicy -> mapper.map(userPolicy, UserPolicyDetailsResDto.class))
	            .collect(Collectors.toList());
	}



	@Override
	public List<UserPolicyDetailsResDto> getSingleUserPolicyDetailsByUserId(Long userId) {
	    List<UserPolicyDetailsResDto> userPolicies = userPolicyDao.findUserPolicyDetailsByUserId(userId);

	    if (userPolicies.isEmpty()) {
	        throw new ResourceNotFoundException("No policy found for user ID: " + userId);
	    }

	    return userPolicies.stream()
	            .map(userPolicy -> mapper.map(userPolicy, UserPolicyDetailsResDto.class))
	            .collect(Collectors.toList());
	}


}
