package hub.policy.service;

import java.io.IOException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hub.policy.dao.UserKycDao;
import hub.policy.dto.ApiResponse;
import hub.policy.dto.KycDto;
import hub.policy.entities.DocumentType;
import hub.policy.entities.KYCStatus;
import hub.policy.entities.UserKyc;

@Service
public class UserKycServiceImpl implements UserKycService{
    @Autowired
	private UserKycDao userKycDao;
    
    @Autowired
    private FileStorageService fileStorageService;
    
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public String verifyKyc(String documentType,String documentNumber,MultipartFile file) throws IOException { 
    	//check if document already exists
    	Optional<UserKyc> existingDoc=userKycDao.findByDocumentNumber(documentNumber);
        if(existingDoc.isPresent())
        	throw new RuntimeException("Document already exists in the system.");
        
        //store file
        String filePath=fileStorageService.saveFile(file);
        
        //save document details
        UserKyc userKyc=new UserKyc();
        userKyc.setDocumentType(DocumentType.valueOf(documentType.toUpperCase()));
        userKyc.setDocumentNumber(documentNumber);
        userKyc.setDocumentUrl(filePath);
        userKyc.setKycStatus(KYCStatus.PENDING);
        
        userKycDao.save(userKyc);
    	
    	return "KYC submitted successfully!";
    	
    }
    
//	public KycDto getUserKycStatus(Long userId) {
//		KYCStatus status=userKycDao.getKycStatus(userId).orElseThrow(() -> new RuntimeException("User has not applied for kyc"));
//		return mapper.map(status, KycDto.class);
//	}

     
}
