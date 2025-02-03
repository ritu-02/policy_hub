package hub.policy.service;

import org.springframework.beans.factory.annotation.Autowired;

import hub.policy.custom_exceptions.KycNotFoundException;
import hub.policy.dao.UserKycDao;
import hub.policy.entities.UserKyc;

public class KycAdminServiceImpl implements KycAdminService {
   @Autowired
	private UserKycDao userKycDao;
	
	@Override
	public String approveKyc(Long documentId) {
		UserKyc userKyc=userKycDao.findById(documentId).orElseThrow(() -> new KycNotFoundException("Kyc request not found"));
		return null;
	}

}
