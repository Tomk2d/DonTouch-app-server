package donTouch.stock_server.dividend.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsStockDividendFixedJpaRepository extends JpaRepository<KrStockDividendExpected, Long> {
}
