package hub.policy.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hub.policy.dao.UserKycDao;
import hub.policy.dto.KycDto;
import hub.policy.entities.KYCStatus;

@Service
@Transactional
public class UserKycStatusServiceImpl implements UserKycStatusService{

	@Autowired
	private UserKycDao userkycDao;
	@Autowired
	private ModelMapper mapper;
	@Override
	public KycDto getUserKycStatus(Long userId) {
		KYCStatus status=userkycDao.getKycStatus(userId);
		return mapper.map(status, KycDto.class);
	}

}
