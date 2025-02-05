package hub.policy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hub.policy.entities.KYCStatus;
import hub.policy.entities.UserKyc;

@Repository
public interface UserKycDao extends JpaRepository<UserKyc, Long>{
  @Query("SELECT k.kycStatus FROM UserKyc k WHERE k.userId = :id ")
  Optional<KYCStatus> getKycStatus(@Param("id") Long id);
  @Query("SELECT k FROM UserKyc k WHERE LOWER(k.documentNumber) = LOWER(:documentNumber)")
  Optional<UserKyc> findByDocumentNumber(@Param("documentNumber") String documentNumber);
  
  boolean existsByDocumentNumber(String documentNumber);


}
