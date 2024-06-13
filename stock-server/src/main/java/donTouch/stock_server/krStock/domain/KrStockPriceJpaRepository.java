package donTouch.stock_server.krStock.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface KrStockPriceJpaRepository extends JpaRepository<KrStockPrice, Integer> {
    List<KrStockPrice> findAllByKrStockIdAndPriceDateGreaterThanEqual(Integer usStockId, LocalDate startDate);
}
