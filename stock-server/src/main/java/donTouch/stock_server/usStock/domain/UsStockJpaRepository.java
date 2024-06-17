package donTouch.stock_server.usStock.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsStockJpaRepository extends JpaRepository<UsStock, Integer> {
    List<UsStock> findByDividendMonth(int month);

    List<UsStock> findDistinctBySymbolContainingOrNameContaining(String symbol, String name);

    List<UsStock> findDistinctBySymbolContainingOrNameContainingAndDividendMonth(String symbol, String name, int month);

}

