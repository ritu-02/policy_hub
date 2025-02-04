package hub.policy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hub.policy.dto.PolicyPlanReqDto;
import hub.policy.entities.PolicyPlan;

@Repository
public interface InsuranceDao extends JpaRepository<PolicyPlan, Long> {

}
