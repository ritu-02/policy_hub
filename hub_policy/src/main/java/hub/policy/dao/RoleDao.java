package hub.policy.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hub.policy.entities.Role;
import hub.policy.entities.UserRole;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleName(UserRole roleName);
	
	Set<Role> findByRoleNameIn(Set<UserRole> roles);

}
