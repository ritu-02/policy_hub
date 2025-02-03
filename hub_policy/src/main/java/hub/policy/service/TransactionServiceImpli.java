package hub.policy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hub.policy.dao.TransactionDao;
import hub.policy.entities.UserPolicy;

@Service

public class TransactionServiceImpli implements TransactionService {
	@Autowired
	private TransactionDao transactionDao;
		
	@Override
	public List<UserPolicy> getAllUserPolicy() {
		return transactionDao.findAll();
	}

	@Override
	public String addNewUserPolicy(UserPolicy obj) {
		transactionDao.save(obj);
		return null;
	}

	@Override
	public UserPolicy getSingleUserPolicy(int id) {
		return transactionDao.findById(id)
				.orElseThrow();	
	}

	@Override
	public String updateUserPolicy(int userPolicyId, UserPolicy userPolicy) {
		if (transactionDao.existsById(userPolicyId)) {		
			transactionDao.save(userPolicy);
			return "Updated userPolicy !";
		}
		return "not found";
	}

	@Override
	public void deleteUserPolicy(int id) {
		if (transactionDao.existsById(id)) {		
			transactionDao.deleteById(id);
		}
	}

}
