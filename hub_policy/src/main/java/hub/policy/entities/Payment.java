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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long paymentId;

	@ManyToOne
	@JoinColumn(name = "user_policy_id", nullable = false) // Foreign Key
	private UserPolicy userPolicy;

	@NotNull
	private double amount;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status", nullable = false)
	private PaymentStatus paymentStatus;

	@Column(name = "payment_date")
	private LocalDateTime paymentDate;

	@Column(name = "transaction_id", nullable = false, unique = true)
	private String transactionId;

	public Payment() {
		super();
	}   
	
	
	
	public Payment(UserPolicy userPolicy, @NotNull double amount, PaymentStatus paymentStatus,
			LocalDateTime paymentDate, String transactionId) {
		super();
		this.userPolicy = userPolicy;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		this.transactionId = transactionId;
	}



	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public UserPolicy getUserPolicy() {
		return userPolicy;
	}

	public void setUserPolicy(UserPolicy userPolicy) {
		this.userPolicy = userPolicy;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus =paymentStatus;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	// ToString
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", userPolicy=" + userPolicy + ", amount=" + amount
				+ ", paymentStatus=" + paymentStatus + ", paymentDate=" + paymentDate + ", transactionId="
				+ transactionId + "]";
	}

}
