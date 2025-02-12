package hub.policy.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hub.policy.custom_exceptions.KycNotFoundException;
import hub.policy.custom_exceptions.ResourceNotFoundException;
import hub.policy.dao.KycDao;
import hub.policy.dao.UserDao;
import hub.policy.dto.KycRequestDto;
import hub.policy.dto.UserKycResDto;
import hub.policy.entities.DocumentType;
import hub.policy.entities.KYCStatus;
import hub.policy.entities.User;
import hub.policy.entities.UserKyc;

@Service
@Transactional
public class KycServiceImpl implements KycService {
    
    @Autowired
    private KycDao kycDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper mapper;

  
    @Override
    public boolean verifyKyc(String documentType, String documentNumber) throws IOException {
        // Validate document type
        DocumentType type;
        try {
            type = DocumentType.valueOf(documentType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid document type: " + documentType);
        }

        // Check if document already exists
        Optional<UserKyc> existingDoc = kycDao.findByDocumentNumber(documentNumber);
        if (existingDoc.isPresent()) {
            throw new RuntimeException("Document already exists in the system.");
        }

        // Save new KYC record
        UserKyc userKyc = new UserKyc();
        userKyc.setDocumentType(type);
        userKyc.setDocumentNumber(documentNumber);
        userKyc.setKycStatus(KYCStatus.PENDING);

        kycDao.save(userKyc);
        return true;
    }

 
    @Override
    public UserKycResDto approveKyc(Long kycId, Long documentId) {
        // Fetch KYC by kycId (Not documentId)
        UserKyc userKyc = kycDao.findById(kycId)
                .orElseThrow(() -> new KycNotFoundException("KYC request not found for ID: " + kycId));

        userKyc.setKycStatus(KYCStatus.VERIFIED);

        // Fetch User by KYC ID
        User user = kycDao.getUserByKycId(kycId)
                .orElseThrow(() -> new ResourceNotFoundException("No user found for KYC ID: " + kycId));

        // Save changes
        UserKyc savedKyc = kycDao.save(userKyc);

        return mapper.map(savedKyc, UserKycResDto.class);
    }

    @Override
    public List<UserKycResDto> getAllKyc() {
        return kycDao.findAll().stream()
        		.map(kyc->mapper.map(kyc, UserKycResDto.class))
        		.collect(Collectors.toList());
    }

 
    @Override
    public UserKycResDto getKycById(Long id) {
        UserKyc kyc = kycDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KYC not found for ID: " + id));

        return mapper.map(kyc, UserKycResDto.class);
    }

    @Override
    public UserKycResDto getKycByUserId(Long userId) {
        Optional<UserKyc> optionalKyc = kycDao.getKycStatus(userId);

        if (optionalKyc.isEmpty()) {
            System.out.println(" No KYC found for user ID: " + userId);
            throw new ResourceNotFoundException("KYC not found for user ID: " + userId);
        }

        return mapper.map(optionalKyc.get(), UserKycResDto.class);
    }

    @Override
    public String submitKyc(KycRequestDto obj) {
        // Validate input
        if (obj.getUserId() == null || obj.getDocumentNumber() == null || obj.getDocumentType() == null) {
            throw new IllegalArgumentException("User ID, document type, and document number are required.");
        }

        // Check if the document number already exists
        if (kycDao.findByDocumentNumber(obj.getDocumentNumber()).isPresent()) {
            throw new RuntimeException("KYC with this document number already exists.");
        }

        // Fetch user from the database using userId
        User user = userDao.findById(obj.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + obj.getUserId()));

        // Check if the user is an admin
        if ("ADMIN".equalsIgnoreCase(user.getUserRole().name())) {
            return "Admin does not need to submit KYC.";
        }

        // Validate document type
        DocumentType documentType;
        try {
            documentType = obj.getDocumentType();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid document type: " + obj.getDocumentType());
        }

        // Create new KYC entry
        UserKyc userKyc = new UserKyc();
        userKyc.setUser(user);
        userKyc.setDocumentType(documentType);
        userKyc.setDocumentNumber(obj.getDocumentNumber());
        userKyc.setKycStatus(KYCStatus.PENDING); // Set initial status as pending

        // Save the KYC entry
        kycDao.save(userKyc);

        return "KYC submitted successfully for user ID: " + obj.getUserId();
    }


    
    
}
