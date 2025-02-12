package hub.policy;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import hub.policy.dao.RoleDao;
import hub.policy.dao.UserDao;
import hub.policy.entities.Role;
import hub.policy.entities.User;
import hub.policy.entities.UserRole;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private RoleDao roleDao;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
		
	@Bean 
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
		           .setMatchingStrategy(MatchingStrategies.STRICT) 
				   .setPropertyCondition(Conditions.isNotNull());
		return modelMapper;
	}

	@Override
	public void run(String... args) throws Exception {
	Role role1 = new Role();
	role1.setRoleName(UserRole.ADMIN);
	Role role2 = new Role();
	role2.setRoleName(UserRole.CUSTOMER);
	Set<Role> roles = new HashSet<Role>();
	roles.add(role1);
	roles.add(role2);
	for(Role role : roles) {
	Optional<Role> existingUser = roleDao.findByRoleName(role.getRoleName());

	            if (existingUser.isEmpty()) {
	                // If the user doesn't exist, insert them
	                roleDao.save(role);
	            }
	}
	}

	
	

	
	
}
