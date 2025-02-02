package hub.policy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hub.policy.entities.KYCStatus;
import hub.policy.entities.UserKyc;

@Repository
public interface UserKycDao extends JpaRepository<UserKyc, Long>{
  @Query("select uk.KycStatus from UserKyc uk where uk.userId= :id ")
  KYCStatus getKycStatus(Long id);
}
