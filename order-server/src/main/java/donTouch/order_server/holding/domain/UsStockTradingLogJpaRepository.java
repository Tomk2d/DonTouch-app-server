package donTouch.order_server.holding.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsStockTradingLogJpaRepository extends JpaRepository<UsStockTradingLog, Long> {
    List<UsStockTradingLog> findAllByUserIdAndUsStockIdIn(Long userId, List<String> stockIds);
}
