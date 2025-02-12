package hub.policy.service;

import java.util.List;

import hub.policy.dto.PolicyPlanAddReqDto;
import hub.policy.dto.PolicyPlanResDto;
import hub.policy.dto.PolicyTypeRequestDto;
import hub.policy.dto.PolicyPlanReqDto;
import hub.policy.entities.PolicyPlan;
import hub.policy.entities.PolicyType;

 public interface PolicyPlanService {
    //method to get all policy plan
	public List<PolicyPlanResDto> getAllPolicyPlan();
    //method to add new plan
	public String addNewPolicyPlan(PolicyPlanAddReqDto addPolicyPlan);
	//method to get a single policy
	public PolicyPlanResDto getSinglePolicyPlan(Long id);
    //method to update policy plan by id
	public String updatePolicyPlan(Long policyPlanId, PolicyPlanReqDto policyPlan);
    // delete a policy plan by id
	public String deletePolicyPlan(Long id);
	// get policy plan by policy type
	public List<PolicyPlanResDto> getPolicyPlanByType(String type);


}
