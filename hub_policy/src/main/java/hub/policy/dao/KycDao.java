package hub.policy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hub.policy.dto.KycDto;
import hub.policy.entities.KYCStatus;
import hub.policy.entities.User;
import hub.policy.entities.UserKyc;

@Repository
public interface KycDao extends JpaRepository<UserKyc, Long>{
	
  @Query("SELECT k FROM UserKyc k WHERE k.user = :id ")
  Optional<UserKyc> getKycStatus(@Param("id") Long id);
  
  @Query("SELECT k FROM UserKyc k WHERE LOWER(k.documentNumber) = LOWER(:documentNumber)")
  Optional<UserKyc> findByDocumentNumber(@Param("documentNumber") String documentNumber);
  
  boolean existsByDocumentNumber(String documentNumber);
  @Query("SELECT k.user FROM UserKyc k WHERE k.id=:id")
  Optional<User> getUserByKycId(Long id);

	@Query("SELECT u FROM UserKyc u JOIN u.user k WHERE k.userId = :id")
	Optional<UserKyc> findKycByUserId(@Param("id") Long userId);


}
