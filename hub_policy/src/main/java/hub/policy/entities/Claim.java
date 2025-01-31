package hub.policy.entities;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="claims")
@Getter
@Setter
@NoArgsConstructor
public class Claim {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="claim_id")
   private Long claimId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_policy",nullable = false)
	private UserPolicy userPolicy;
	
	@Column(name="claim_date")
	private LocalDateTime claimDate;
	
	@Column(name="claim_amount",nullable = false)
	private double claimAmount;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="claim_status",columnDefinition = "ENUM('PENDING','APPROVED','REJECTED') DEFAULT 'PENDING'")
	private ClaimStatus claimStatus;
	
	@Column(name="document_url")
	private String documentUrl;
	
	
}
