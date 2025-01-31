package hub.policy.entities;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="policies")
public class PolicyPlan {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="plan_id")
   private Long policyPlanId;
   
   @Column(name="plan_name", nullable=false)
   private String policyPlanName;
   
   @Column
   private String description;
   
   @Column(nullable=false)
   private double premium;
   
   @Column(name="coverage_amount",nullable=false)
   private double coverageAmount;
   
   @Column(nullable=false)
   private int duration;
   
   @Column(name="created_at")
   @CreationTimestamp
   private LocalDateTime createdAt;
   
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name="policy_type")
   private PolicyType policyType;
   
 
   
}
