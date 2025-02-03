package hub.policy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hub.policy.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
   Optional<User> findByEmailAndPassword(String em,String pass);
   Optional<User> findByEmail(String email);
}
