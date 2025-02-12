package hub.policy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hub.policy.dto.PaymentResDto;
import hub.policy.dto.PaymentUserResDto;
import hub.policy.entities.Payment;
import hub.policy.entities.UserPolicy;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Long> {

	@Query("SELECT new hub.policy.dto.PaymentUserResDto(p.id, p.amount, p.paymentStatus, " +
		       "u.user.id, u.user.email, u.policyPlan.id, u.policyPlan.policyPlanName) " +
		       "FROM Payment p JOIN p.userPolicy u WHERE u.user.id = :id")
		List<PaymentUserResDto> findPaymentsByUserId(@Param("id") Long userId);


	
	@Query("SELECT p FROM Payment p JOIN FETCH p.userPolicy WHERE p.userPolicy.id = :id")
	List<Payment> findPaymentsByUserPolicyId(@Param("id") Long id);

	


}
