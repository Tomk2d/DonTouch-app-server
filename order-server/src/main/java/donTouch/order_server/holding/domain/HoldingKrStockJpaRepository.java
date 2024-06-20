package donTouch.order_server.holding.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldingKrStockJpaRepository extends JpaRepository<HoldingKrStock, Long> {

    Optional<HoldingKrStock> findByUserIdAndKrStockId(Long userId, String krStockId);
}
