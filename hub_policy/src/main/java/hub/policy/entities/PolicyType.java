package hub.policy.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "policy_types")
public class PolicyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long typeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "insurance_type", length = 50, nullable = false)
    @NotNull
    private InsuranceType insuranceType;

    @Column(nullable = false)
    @NotNull
    private String description;

    @OneToMany(mappedBy = "policyType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PolicyPlan> policyPlans = new ArrayList<>();

    public PolicyType() {}

    public PolicyType(InsuranceType insuranceType, String description) {
        this.insuranceType = insuranceType;
        this.description = description;
    }

    public Long getTypeId() { return typeId; }
    public void setTypeId(Long typeId) { this.typeId = typeId; }

    public InsuranceType getInsuranceType() { return insuranceType; }
    public void setInsuranceType(InsuranceType insuranceType) { this.insuranceType = insuranceType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<PolicyPlan> getPolicyPlans() { return policyPlans; }
    
    public void addPolicyPlan(PolicyPlan plan) {
        policyPlans.add(plan);
        plan.setPolicyType(this);
    }

    public void removePolicyPlan(PolicyPlan plan) {
        policyPlans.remove(plan);
        plan.setPolicyType(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyType that = (PolicyType) o;
        return typeId != null && typeId.equals(that.typeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "PolicyType [typeId=" + typeId + ", insuranceType=" + insuranceType + ", description=" + description + "]";
    }
}
