package hub.policy.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_policies")
public class UserPolicy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_policy_id")
	private Long userPolicyId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="policy_id")
	private Long policyId;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = " ENUM('ACTIVE','EXPIRED','CANCELLED') DEFAULT 'ACTIVE'")
	private Status status;

}
