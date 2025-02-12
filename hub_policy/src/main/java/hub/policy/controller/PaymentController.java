package hub.policy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hub.policy.dto.ApiResponse;
import hub.policy.dto.PaymentReqDto;
import hub.policy.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000")// Allow frontend access
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<?> getAllPayments() {
        return ResponseEntity.ok(new ApiResponse("All Payments Retrieved", paymentService.getAllPayment()));
    }

    @GetMapping("/user/{userid}")
    public ResponseEntity<?> getSinglePaymentByUserId(@PathVariable Long userid) {
        return ResponseEntity.ok(new ApiResponse("Payment details retrieved for user", paymentService.getSinglePaymentByUserId(userid)));
    }

    @GetMapping("/user-policy/{userpolicyid}")
    public ResponseEntity<?> getAllPaymentByUserPolicyId(@PathVariable Long userpolicyid) {
        return ResponseEntity.ok(new ApiResponse("Payment details retrieved for policy", paymentService.getAllPaymentByUserPolicyId(userpolicyid)));
    }

    @PostMapping("/user-policy/{userpolicyid}")
    public ResponseEntity<?> makePayment(@PathVariable Long userpolicyid, @RequestBody PaymentReqDto obj) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Payment successful", paymentService.addPayment(userpolicyid, obj)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse("Payment failed: " + e.getMessage(), null));
        }
    }


  
}
