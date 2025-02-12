package hub.policy.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import hub.policy.entities.PaymentStatus;

public class PaymentReqDto {
	@JsonProperty(access = Access.READ_ONLY)
	private Long userPolicyId;
	private Long paymentId;
	private double amount;
	private PaymentStatus paymentStatus;
	private LocalDateTime paymentDate;
	private String transactionId;
	
	public PaymentReqDto() {
		super();
	}

	public PaymentReqDto(Long userPolicyId, double amount, String paymentStatus,String paymentDate,
			String transactionId) {
		super();
		this.userPolicyId = userPolicyId;
		this.amount = amount;
		this.paymentStatus = PaymentStatus.valueOf(paymentStatus.toUpperCase());
		this.paymentDate = LocalDateTime.parse(paymentDate);
		this.transactionId = transactionId;
	}



	public Long getUserPolicyId() {
		return userPolicyId;
	}

	public void setUserPolicyId(Long userPolicyId) {
		this.userPolicyId = userPolicyId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
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

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = PaymentStatus.valueOf(paymentStatus.toUpperCase());
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate =LocalDateTime.parse(paymentDate);
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "PaymentReqDto [userPolicyId=" + userPolicyId + ", paymentId=" + paymentId + ", amount=" + amount
				+ ", paymentStatus=" + paymentStatus + ", paymentDate=" + paymentDate + ", transactionId="
				+ transactionId + "]";
	}
	
	
	
	
	
}
