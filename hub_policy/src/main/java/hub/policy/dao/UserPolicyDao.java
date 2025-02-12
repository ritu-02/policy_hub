package hub.policy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hub.policy.dto.UserPolicyDetailsResDto;
import hub.policy.entities.UserPolicy;

@Repository
public interface UserPolicyDao extends JpaRepository<UserPolicy, Long> {

	@Query("SELECT new hub.policy.dto.UserPolicyDetailsResDto(p.id,p.policyPlan.policyPlanId ,p.policyPlan.policyPlanName, p.user.userId, p.user.email,p.startDate,p.endDate) "
			+ "FROM UserPolicy p WHERE p.user.id = :userId")
	List<UserPolicyDetailsResDto> findUserPolicyDetailsByUserId(@Param("userId") Long userId);

	@Query("SELECT new hub.policy.dto.UserPolicyDetailsResDto(p.id, p.policyPlan.policyPlanId, p.policyPlan.policyPlanName, " +
		       "p.user.userId, p.user.email, p.startDate, p.endDate) " +
		       "FROM UserPolicy p")
		List<UserPolicyDetailsResDto> getAllUserPolicies();


}
