package hub.policy.service;

import java.util.List;

import hub.policy.entities.UserPolicy;

public interface TransactionService {

	public List<UserPolicy> getAllUserPolicy();

	public String addNewUserPolicy(UserPolicy userPolicy);

	public UserPolicy getSingleUserPolicy(int id);

	public String updateUserPolicy(int userPolicyId, UserPolicy userPolicy);

	public void deleteUserPolicy(int id);

}
