package hub.policy.dto;

import hub.policy.entities.PaymentStatus;

public class PaymentUserResDto {
     private Long paymentId;
     private double amount;
     private PaymentStatus paymentStatus;
     private Long userId;
     private String email;
     private Long policyPlanId;
     private String policyplanName;
     
	public PaymentUserResDto() {
		super();
	}



	public PaymentUserResDto(Long paymentId, double amount, PaymentStatus paymentStatus, Long userId, String email,
			Long policyPlanId, String policyplanName) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.userId = userId;
		this.email = email;
		this.policyPlanId = policyPlanId;
		this.policyplanName = policyplanName;
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

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPolicyPlanId() {
		return policyPlanId;
	}

	public void setPolicyPlanId(Long policyPlanId) {
		this.policyPlanId = policyPlanId;
	}

	public String getPolicyplanName() {
		return policyplanName;
	}

	public void setPolicyplanName(String policyplanName) {
		this.policyplanName = policyplanName;
	}

	@Override
	public String toString() {
		return "PaymentUserResDto [paymentId=" + paymentId + ", amount=" + amount + ", paymentStatus=" + paymentStatus
				+ ", userId=" + userId + ", email=" + email + ", policyPlanId=" + policyPlanId + ", policyplanName="
				+ policyplanName + "]";
	}
	
	
     
     
     
}
