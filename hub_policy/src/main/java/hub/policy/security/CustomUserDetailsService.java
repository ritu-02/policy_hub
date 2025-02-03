package hub.policy.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import hub.policy.custom_exceptions.UsernameNotFoundException;
import hub.policy.dao.UserDao;
import hub.policy.entities.User;

@Service
@Transactional
public class CustomUserDetailsService {
   @Autowired
	private UserDao userDao;
   
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
    	User user =userDao.findByEmail(email)
    			          .orElseThrow(()-> new UsernameNotFoundException("Email not found"));
    	return new CustomUserDetails(user);
    }
}
