package hub.policy.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
