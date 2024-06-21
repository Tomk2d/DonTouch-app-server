package donTouch.order_server.holding.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KrStockTradingLogJpaRepository extends JpaRepository<KrStockTradingLog, Long> {
    List<KrStockTradingLog> findAllByUserIdAndKrStockIdIn(Long userId, List<String> krStockIds);
}
