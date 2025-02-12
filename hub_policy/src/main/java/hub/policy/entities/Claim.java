package hub.policy.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="claims")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Claim {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="claim_id")
    private Long claimId;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="user_policy_id", nullable = false)
    private UserPolicy userPolicy;

    @Column(name="claim_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime claimDate;

    @Column(name="claim_amount", nullable = false)
    private double claimAmount;

    @Enumerated(EnumType.STRING)
    @Column(name="claim_status", nullable = false)
    private ClaimStatus claimStatus = ClaimStatus.PENDING;
}
