package hub.policy.service;

import java.util.List;

import hub.policy.dto.UserResponseDto;

public interface UserAdminService {
	List<UserResponseDto> getAllUsersList();

}
