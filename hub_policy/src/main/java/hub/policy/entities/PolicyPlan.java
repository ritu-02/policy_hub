package hub.policy.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "policy_plans")
public class PolicyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long policyPlanId;

    @Column(name = "plan_name", nullable = false)
    private String policyPlanName;

    @Column
    private String description;

    @Column(nullable = false)
    private double premium;

    @Column(name = "sum_insured", nullable = false)
    private double sumInsured;

    @Column(nullable = false)
    private String term;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_type_id", nullable = false)
    @JsonBackReference // Prevents infinite recursion
    private PolicyType policyType;

    @Column(nullable = false)
    private String eligibility;

    public PolicyPlan() {}

    public PolicyPlan(String policyPlanName, String description, double premium, double sumInsured, String term,
                      String eligibility, PolicyType policyType) {
        this.policyPlanName = policyPlanName;
        this.description = description;
        this.premium = premium;
        this.sumInsured = sumInsured;
        this.term = term;
        this.eligibility = eligibility;
        this.policyType = policyType;
    }

    public Long getPolicyPlanId() { return policyPlanId; }
    public void setPolicyPlanId(Long policyPlanId) { this.policyPlanId = policyPlanId; }

    public String getPolicyPlanName() { return policyPlanName; }
    public void setPolicyPlanName(String policyPlanName) { this.policyPlanName = policyPlanName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPremium() { return premium; }
    public void setPremium(double premium) { this.premium = premium; }

    public double getSumInsured() { return sumInsured; }
    public void setSumInsured(double sumInsured) { this.sumInsured = sumInsured; }

    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public PolicyType getPolicyType() { return policyType; }
    public void setPolicyType(PolicyType policyType) { this.policyType = policyType; }

    public String getEligibility() { return eligibility; }
    public void setEligibility(String eligibility) { this.eligibility = eligibility; }

	@Override
	public String toString() {
		return "PolicyPlan [policyPlanName=" + policyPlanName + ", description=" + description + ", premium=" + premium
				+ ", sumInsured=" + sumInsured + ", term=" + term + ", eligibility="
				+ eligibility + "]";
	}
         
	
  
}
