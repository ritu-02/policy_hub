package hub.policy.service;

import java.util.List;

import hub.policy.entities.PolicyPlan;

 public interface InsuranceService {

	public List<PolicyPlan> getAllPolicyPlan();

	public String addNewPolicyPlan(PolicyPlan policyPlan);
	
	public PolicyPlan getSinglePolicyPlan(Long id);

	public String updatePolicyPlan(Long policyPlanId, PolicyPlan policyPlan);

	public void deletePolicyPlan(Long id);


}
