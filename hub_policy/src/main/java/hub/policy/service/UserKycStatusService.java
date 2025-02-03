package hub.policy.service;

import hub.policy.dto.KycDto;

public interface UserKycStatusService {
   KycDto getUserKycStatus(Long userId);
}
