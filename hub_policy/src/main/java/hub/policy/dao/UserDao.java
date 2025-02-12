package hub.policy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hub.policy.dto.KycDto;
import hub.policy.entities.KYCStatus;
import hub.policy.entities.Payment;
import hub.policy.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
	Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);


	@Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
	Optional<User> findByEmail(@Param("email") String email);

}
