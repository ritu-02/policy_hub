package hub.policy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hub.policy.entities.UserPolicy;

public interface TransactionDao extends JpaRepository<UserPolicy, Integer> {

}
