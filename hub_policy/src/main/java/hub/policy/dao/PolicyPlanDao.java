package hub.policy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hub.policy.dto.PolicyPlanResDto;
import hub.policy.entities.InsuranceType;
import hub.policy.entities.PolicyPlan;

@Repository
public interface PolicyPlanDao extends JpaRepository<PolicyPlan, Long> {
	

	@Query("SELECT p FROM PolicyPlan p WHERE p.policyPlanName = :planName ")
	Optional<PolicyPlan> findByPlanName(@Param("planName") String planName);

	@Query("SELECT p FROM PolicyPlan p WHERE p.policyType.typeId = :policyTypeId")
	List<PolicyPlan> findByPolicyTypeId(@Param("policyTypeId") Long policyTypeId);

	

}
