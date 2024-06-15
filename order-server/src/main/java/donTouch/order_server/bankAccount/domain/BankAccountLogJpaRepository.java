package donTouch.order_server.bankAccount.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountLogJpaRepository extends JpaRepository<BankAccountLog, Long> {

}
