package donTouch.stock_server.usStock.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsStockJpaRepository extends JpaRepository<UsStock, Integer> {
    List<UsStock> findAllByDividendMonth(Integer month);

    List<UsStock> findDistinctBySymbolContainingOrNameContainingOrEnglishNameContaining(String symbol, String name, String englishName);
}

