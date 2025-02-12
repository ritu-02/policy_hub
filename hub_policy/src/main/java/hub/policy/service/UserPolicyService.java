package hub.policy.service;

import java.util.List;

import hub.policy.dto.UserPolicyDetailsResDto;
import hub.policy.dto.UserPolicyReqDto;

public interface UserPolicyService {

	UserPolicyDetailsResDto purchasedPolicyPlan(UserPolicyReqDto obj);
	
	List<UserPolicyDetailsResDto> getAllUserPolicyDetails();
	
	List<UserPolicyDetailsResDto> getSingleUserPolicyDetailsByUserId(Long userid);
	

}