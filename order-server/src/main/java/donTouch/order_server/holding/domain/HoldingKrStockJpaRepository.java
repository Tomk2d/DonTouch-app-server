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
public interface HoldingKrStockJpaRepository extends JpaRepository<HoldingKrStock, Long> {
    public Optional<HoldingKrStock> findByUserIdAndKrStockId(Long userId, String krStockId);
    public List<HoldingKrStock> findAllByUserId(Long userId);
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT h FROM HoldingKrStock h WHERE h.user.id = :userId AND h.krStockId = :krStockId")
    public Optional<HoldingKrStock> findByUserIdAndKrStockIdWithLock(@Param("userId") Long userId, @Param("krStockId") String krStockId);
}
