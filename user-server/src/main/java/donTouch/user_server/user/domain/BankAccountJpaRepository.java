package donTouch.user_server.user.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountJpaRepository extends JpaRepository<BankAccount, Long> {
    public Optional<BankAccount> findByUserId(Long userId);
}
