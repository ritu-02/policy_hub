package hub.policy.service;

import java.util.List;

import hub.policy.dto.AddPolicyTypeReqDto;
import hub.policy.dto.PolicyTypeDto;

public interface PolicyTypeService {
   //add a policy type
	PolicyTypeDto addPolicyType(AddPolicyTypeReqDto policyObj);
	//update policy detail of specific id
	PolicyTypeDto updatePolicyTypeById(Long id,AddPolicyTypeReqDto obj);
	//delete a policy type
	String deletePolicyTypeById(Long id);
	//get all policy details
	List<PolicyTypeDto> getAllPolicyTypes();
	//get a single policy details by id
	PolicyTypeDto getPolicyTypeById(Long id);
}
