package hub.policy.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
