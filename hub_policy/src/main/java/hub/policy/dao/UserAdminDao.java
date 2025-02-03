package hub.policy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hub.policy.entities.User;

@Repository
public interface UserAdminDao extends JpaRepository<User, Long> {
   
}
