package donTouch.stock_server.dividend.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UsStockDividendExpectedJpaRepository extends JpaRepository<UsStockDividendExpected, Long> {
    List<UsStockDividendExpected> findAllBySymbolInAndDividendDateBetween(LocalDate startDate, LocalDate endDate, List<String> symbols);

}
