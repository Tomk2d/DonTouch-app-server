package donTouch.stock_server.usStock.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsStockJpaRepository extends JpaRepository<UsStock, Integer> {
    List<UsStock> findAllByDividendMonth(Integer month);

    Optional<UsStock> findBySymbol(String symbol);

    List<UsStock> findDistinctBySymbolContainingOrNameContainingOrEnglishNameContaining(String symbol, String name, String englishName);
}

