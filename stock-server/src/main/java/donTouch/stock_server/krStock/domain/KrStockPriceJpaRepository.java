package donTouch.stock_server.krStock.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KrStockPriceJpaRepository extends JpaRepository<KrStockPrice, Integer> {
}
