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
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="payments")
@Getter
@Setter
@ToString
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="payment_id")
	private Long paymentId;
  
  @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_policy",nullable = false)
	private UserPolicy userPolicy;
  
  @NotNull
  private double amount;
  
  @Enumerated(EnumType.STRING)
  @Column(name="payment_status",nullable = false)
  private PaymentStatus paymentStatus;
  
  @Column(name="payment_date")
  private LocalDateTime paymentDate;
  
  @Column(name="transaction_id",nullable=false,unique=true)
  private String transactionId;
}
