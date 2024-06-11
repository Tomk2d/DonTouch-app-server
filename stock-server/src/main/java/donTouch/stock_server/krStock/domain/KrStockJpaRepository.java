package donTouch.stock_server.krStock.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KrStockJpaRepository extends JpaRepository<KrStock, Integer> {
}
