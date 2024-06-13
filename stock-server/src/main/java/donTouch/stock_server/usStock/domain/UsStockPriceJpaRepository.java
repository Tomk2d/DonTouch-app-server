package donTouch.stock_server.usStock.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UsStockPriceJpaRepository extends JpaRepository<UsStockPrice, Long> {
    List<UsStockPrice> findAllByUsStockIdAndPriceDateGreaterThanEqual(Integer usStockId, LocalDate startDate);
}
