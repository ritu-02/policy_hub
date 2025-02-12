package hub.policy.entities;

import java.time.LocalDateTime;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import lombok.*;

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
