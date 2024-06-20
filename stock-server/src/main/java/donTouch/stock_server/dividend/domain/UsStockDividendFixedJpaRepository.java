package donTouch.stock_server.dividend.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UsStockDividendFixedJpaRepository extends JpaRepository<UsStockDividendFixed, Long> {
    List<UsStockDividendFixed> findAllByDividendDateBetweenAndSymbolIn(LocalDate startDate, LocalDate endDate, List<String> symbols);

}
