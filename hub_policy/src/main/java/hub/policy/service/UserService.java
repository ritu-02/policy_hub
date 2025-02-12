package hub.policy.service;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hub.policy.dao.UserDao;
import hub.policy.dto.AdminResponseDTO;


@Service
@Transactional
public class UserService {
	//to fetch all user details
    @Autowired
    private UserDao userDao;
    @Autowired
    private ModelMapper mapper;
    
	public List<AdminResponseDTO> getAllUsersList() {
		return userDao.findAll().stream().map(user -> mapper.map(user, AdminResponseDTO.class))
				.collect(Collectors.toList());
	}
}
