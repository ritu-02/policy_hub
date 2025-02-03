package hub.policy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hub.policy.dao.InsuranceDao;
import hub.policy.entities.PolicyPlan;

@Service

public class InsuranceServiceImpli implements InsuranceService {
	@Autowired
	private InsuranceDao insuranceDao;

	@Override
	public List<PolicyPlan> getAllPolicyPlan() {
		return insuranceDao.findAll();
	}

	@Override
	public String addNewPolicyPlan(PolicyPlan obj) {
		insuranceDao.save(obj);
		return "policy plan added";
	}

	@Override
	public PolicyPlan getSinglePolicyPlan(Long id) {
		return insuranceDao.findById(id)
				.orElseThrow();
	}

	@Override
	public String updatePolicyPlan(Long policyPlanId, PolicyPlan policyPlan) {
		if (insuranceDao.existsById(policyPlanId)) {		
			insuranceDao.save(policyPlan);
			return "Updated category !";
		}
		return "not found";
	}

	@Override
	public void deletePolicyPlan(Long id) {
		if (insuranceDao.existsById(id)) {		
			insuranceDao.deleteById(id);
		}		
	}

}
