package hub.policy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hub.policy.custom_exceptions.KycNotFoundException;
import hub.policy.dao.UserKycDao;
import hub.policy.entities.KYCStatus;
import hub.policy.entities.UserKyc;

@Service
@Transactional
public class KycAdminServiceImpl implements KycAdminService {
   @Autowired
	private UserKycDao userKycDao;
	
	@Override
	public String approveKyc(Long documentId) {
		UserKyc userKyc=userKycDao.findById(documentId).orElseThrow(() -> new KycNotFoundException("Kyc request not found"));
		userKyc.setKycStatus(KYCStatus.VERIFIED);
		userKycDao.save(userKyc);
		return "KYC approved successfully for document ID:"+documentId;
	}

}
