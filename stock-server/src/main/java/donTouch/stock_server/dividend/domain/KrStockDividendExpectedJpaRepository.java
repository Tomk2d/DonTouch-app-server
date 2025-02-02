package donTouch.stock_server.dividend.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface KrStockDividendExpectedJpaRepository extends JpaRepository<KrStockDividendExpected, Long> {
    List<KrStockDividendExpected> findAllBySymbolInAndDividendDateBetween(List<String> symbols, LocalDate startDate, LocalDate endDate);
}
