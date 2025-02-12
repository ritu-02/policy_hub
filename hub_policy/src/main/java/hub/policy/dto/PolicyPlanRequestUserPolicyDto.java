package hub.policy.dto;

public class PolicyPlanRequestUserPolicyDto {
	 private String policyPlanName;

	 
	public PolicyPlanRequestUserPolicyDto() {
		super();
	}

	public String getPolicyPlanName() {
		return policyPlanName;
	}

	public void setPolicyPlanName(String policyPlanName) {
		this.policyPlanName = policyPlanName;
	}

	@Override
	public String toString() {
		return "PolicyPlanRequestUserPolicyDto [policyPlanName=" + policyPlanName + "]";
	}
	
	
	 
}
