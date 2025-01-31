package hub.policy.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="policy_types")
@Getter
@Setter
@ToString
public class PolicyType {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "type_id")
   private Long typeId;
   
   @Column(name="insurance_type",length=50)
   @Enumerated(EnumType.STRING)
   private InsuranceType insuranceType;
   
   @Column
   private String description;
   

	
}
