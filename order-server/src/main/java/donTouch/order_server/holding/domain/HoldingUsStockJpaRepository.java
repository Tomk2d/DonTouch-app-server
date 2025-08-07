package donTouch.order_server.holding.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface HoldingUsStockJpaRepository extends JpaRepository<HoldingUsStock, Long> {
    public Optional<HoldingUsStock> findByUserIdAndUsStockId(Long userId, String usStockId);
    public List<HoldingUsStock> findAllByUserId(Long userId);
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT h FROM HoldingUsStock h WHERE h.user.id = :userId AND h.usStockId = :usStockId")
    public Optional<HoldingUsStock> findByUserIdAndUsStockIdWithLock(@Param("userId") Long userId, @Param("usStockId") String usStockId);
}
