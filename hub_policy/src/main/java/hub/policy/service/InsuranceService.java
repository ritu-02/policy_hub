package hub.policy.service;

import java.util.List;

import hub.policy.dto.PolicyPlanAddReqDto;
import hub.policy.dto.PolicyPlanResponseDto;
import hub.policy.dto.PolicyPlanReqDto;
import hub.policy.entities.PolicyPlan;

 public interface InsuranceService {

	public List<PolicyPlanResponseDto> getAllPolicyPlan();

	public String addNewPolicyPlan(PolicyPlanAddReqDto addPolicyPlan);
	
	public PolicyPlanResponseDto getSinglePolicyPlan(Long id);

	public String updatePolicyPlan(Long policyPlanId, PolicyPlanReqDto policyPlan);

	public String deletePolicyPlan(Long id);


}
