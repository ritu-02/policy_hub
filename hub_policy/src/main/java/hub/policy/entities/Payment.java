package hub.policy.entities;

import java.time.LocalDateTime;

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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="payments")
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
