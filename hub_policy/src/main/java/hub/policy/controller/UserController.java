package hub.policy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hub.policy.dto.ApiResponse;
import hub.policy.dto.ForgetPasswordReqDto;
import hub.policy.dto.UpdateUserReqDto;
import hub.policy.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")// Enable CORS for frontend
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse("User retrieved successfully", userService.getUserById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserDetails(@PathVariable Long id, @RequestBody UpdateUserReqDto obj) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse("User updated successfully", userService.updateUserDetailsById(id,obj)));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgetPassword(@RequestBody ForgetPasswordReqDto obj) {
        return ResponseEntity.ok(new ApiResponse("Password reset link sent successfully", userService.forgetPassword(obj)));
    }

    @GetMapping("/kyc/{userid}")
    public ResponseEntity<?> getUserKycStatus(@PathVariable Long userid) {
        return ResponseEntity.ok(new ApiResponse("KYC status retrieved successfully", userService.getUserKycStatus(userid)));
    }
    

  
}
