package donTouch.order_server.holding.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KrStockTradingLogJpaRepository extends JpaRepository<KrStockTradingLog, Long> {

}
