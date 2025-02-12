package hub.policy.dto;

import java.time.LocalDateTime;

public class PaymentResDto {
	private Long paymentId;
    private Double amount;
    private String paymentStatus;
    private String transactionId;
    private LocalDateTime paymentDate;
    
    
	public PaymentResDto() {
		super();
	}


	public PaymentResDto(Long paymentId,  Long policyId, String paymentStatus, String transactionId,String paymentDate,Double amount) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.transactionId = transactionId;
		this.paymentDate=LocalDateTime.parse(paymentDate);
	}


	public Long getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}


	@Override
	public String toString() {
		return "PaymentResDto [paymentId=" + paymentId + ", amount=" + amount + ", paymentStatus=" + paymentStatus
				+", transactionId=" + transactionId
				+ ", paymentDate=" + paymentDate + "]";
	}

	
	
    
    
}
