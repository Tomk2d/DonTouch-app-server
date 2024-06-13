package donTouch.stock_server.dividend.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface KrStockDividendFixedJpaRepository extends JpaRepository<KrStockDividendFixed, Long> {
    List<KrStockDividendFixed> findAllByDividendDateBetweenAndKrStockIdIn(LocalDate startDate, LocalDate endDate, List<Integer> krStockIds);

}
