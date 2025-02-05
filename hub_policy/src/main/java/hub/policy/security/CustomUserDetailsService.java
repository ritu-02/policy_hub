package hub.policy.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import hub.policy.custom_exceptions.UsernameNotFoundException;
import hub.policy.dao.UserDao;
import hub.policy.entities.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserDao userDao;
   
   public CustomUserDetailsService(UserDao userDao) {
	   this.userDao=userDao;
   }

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Optional<User> user=userDao.findByEmail(username);
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.get().getEmail())
				.password(user.get().getPassword())
				.roles(user.get().getUserRole().name())
				.build();
	}
}
