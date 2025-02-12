package hub.policy.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import hub.policy.dto.KycRequestDto;
import hub.policy.dto.UserKycResDto;
import hub.policy.entities.UserKyc;

public interface KycService {
	//method to approve kyc document
   UserKycResDto approveKyc(Long kycId,Long userId);
   //method to get all kyc apllications
   List<UserKycResDto> getAllKyc();
   //method to get single kyc application
   UserKycResDto getKycById(Long id);
   //method to verify kyc by admin
   boolean verifyKyc(String documentType,String documentNumber) throws IOException;
   //method to get kyc by user id
   UserKycResDto getKycByUserId(Long id);
   //method to submit kyc
   String submitKyc(KycRequestDto obj);
}
