package donTouch.order_server.holding.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoldingUsStockJpaRepository extends JpaRepository<HoldingUsStock, Long> {
    Optional<HoldingUsStock> findByUserIdAndUsStockId(Long userId, String krStockId);

    List<HoldingUsStock> findAllByUserId(Long userId);
}
