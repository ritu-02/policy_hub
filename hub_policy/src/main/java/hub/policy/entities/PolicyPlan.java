package hub.policy.entities;


import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="policy_plans")
@Setter
@Getter
@ToString
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
