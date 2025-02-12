package hub.policy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hub.policy.custom_exceptions.ResourceNotFoundException;
import hub.policy.dao.PaymentDao;
import hub.policy.dao.UserPolicyDao;
import hub.policy.dto.PaymentReqDto;
import hub.policy.dto.PaymentResDto;
import hub.policy.dto.PaymentUserResDto;
import hub.policy.entities.Payment;
import hub.policy.entities.PaymentStatus;
import hub.policy.entities.UserPolicy;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private UserPolicyDao userPolicyDao;
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<PaymentResDto> getAllPayment() {
		return paymentDao
				  .findAll()
				  .stream()
				  .map(payment -> mapper.map(payment,PaymentResDto.class) )
				  .collect(Collectors.toList());
	}

	@Override
	public PaymentResDto getSinglePaymentByUserId(Long userId) {
		
		List<PaymentUserResDto> payment=paymentDao.findPaymentsByUserId(userId);
		return mapper.map(payment, PaymentResDto.class);
	}
	
	@Override
	public PaymentResDto getAllPaymentByUserPolicyId(Long policyId) {
		List<Payment> payment=paymentDao.findPaymentsByUserPolicyId(policyId);
		return mapper.map(payment, PaymentResDto.class);
	}

	@Override
	public String addPayment(Long userPolicyId, PaymentReqDto req) {
	    // Fetch the UserPolicy from the database
	    UserPolicy userPolicy = userPolicyDao.findById(userPolicyId)
	            .orElseThrow(() -> new ResourceNotFoundException("Invalid User Policy ID: " + userPolicyId));

	    // Create a new Payment entity
	    Payment payment = new Payment();
	    payment.setUserPolicy(userPolicy);
	    payment.setAmount(req.getAmount());
	    payment.setPaymentDate(req.getPaymentDate());
	    payment.setTransactionId(req.getTransactionId());
	    payment.setPaymentStatus(req.getPaymentStatus()); // Assuming successful payment

	    // Save the payment record
	    paymentDao.save(payment);

	    return "Payment successfully added for UserPolicy ID: " + userPolicyId;
	}

	

}
