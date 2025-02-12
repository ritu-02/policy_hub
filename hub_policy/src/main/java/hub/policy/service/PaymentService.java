package hub.policy.service;

import java.util.List;

import hub.policy.dto.PaymentReqDto;
import hub.policy.dto.PaymentResDto;

public interface PaymentService {

	public List<PaymentResDto> getAllPayment();

	public PaymentResDto getSinglePaymentByUserId(Long userId);
 
	public PaymentResDto getAllPaymentByUserPolicyId(Long id);
	
	public String addPayment(Long userPolicyId,PaymentReqDto req);

}
