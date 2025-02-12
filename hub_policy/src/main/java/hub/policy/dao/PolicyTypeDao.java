package hub.policy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hub.policy.entities.InsuranceType;
import hub.policy.entities.PolicyType;

@Repository
public interface PolicyTypeDao extends JpaRepository<PolicyType,Long> {
	//@Query("SELECT p FROM PolicyType p WHERE p.insuranceType = :type")
	//PolicyType findByType(@Param("type") InsuranceType type);

	Optional<PolicyType> findByInsuranceType(InsuranceType insuranceType);

}
