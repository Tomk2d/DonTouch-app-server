package donTouch.stock_server.usStock.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsStockPriceJpaRepository extends JpaRepository<UsStockPrice, Long> {
    List<UsStockPrice> findAllByUsStockIdAndDateGreaterThanEqual(Integer usStockId, LocalDate startDate);

    Optional<UsStockPrice> findTopByUsStockIdOrderByDate(Integer usStockId);
}
