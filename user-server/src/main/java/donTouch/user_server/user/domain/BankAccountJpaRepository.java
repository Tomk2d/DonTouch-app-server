package donTouch.user_server.user.domain;

import java.util.Optional;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.persistence.QueryHint;

@Repository
public interface BankAccountJpaRepository extends JpaRepository<BankAccount, Long> {
    public Optional<BankAccount> findByUserId(Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = "javax.persistence.lock.timeout", value = "3000"))
    @Query("SELECT b FROM BankAccount b WHERE b.user.id = :userId")
    public Optional<BankAccount> findByUserIdForUpdate(@Param("userId") Long userId);
}
