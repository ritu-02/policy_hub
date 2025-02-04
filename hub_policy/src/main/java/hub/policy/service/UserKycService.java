package hub.policy.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import hub.policy.dto.KycDto;

public interface UserKycService {
	 public String verifyKyc(String documentType,String documentNumber,MultipartFile file) throws IOException;
	 
	 public KycDto getUserKycStatus(Long userId);

}
