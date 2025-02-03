package hub.policy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hub.policy.dao.UserAdminDao;
import hub.policy.dto.UserResponseDto;

@Service
public class UserAdminServiceImpl implements UserAdminService {
	@Autowired
	private UserAdminDao userAdminDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<UserResponseDto> getAllUsersList() {
		return userAdminDao.findAll().stream()
				.map(user -> mapper.map(user, UserResponseDto.class))
				.collect(Collectors.toList());
	}

}
