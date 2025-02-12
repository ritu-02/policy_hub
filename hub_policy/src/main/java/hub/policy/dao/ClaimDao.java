package hub.policy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hub.policy.entities.Claim;

@Repository
public interface ClaimDao extends JpaRepository<Claim, Long>{

}
