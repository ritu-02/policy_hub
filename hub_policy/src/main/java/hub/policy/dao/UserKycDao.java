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
//  @Query("select k.KycStatus from UserKyc k where k.userId= :id ")
//  Optional<KYCStatus> getKycStatus(Long id);
  
  Optional<UserKyc> findByDocumentNumber(String documentNumber);
  
  boolean existsByDocumentNumber(String documentNumber);


}
