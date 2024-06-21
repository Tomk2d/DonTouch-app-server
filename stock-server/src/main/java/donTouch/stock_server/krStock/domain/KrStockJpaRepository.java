package donTouch.stock_server.krStock.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KrStockJpaRepository extends JpaRepository<KrStock, Integer> {
    List<KrStock> findAllByDividendMonth(Integer month);

    Optional<KrStock> findBySymbol(String symbol);

    List<KrStock> findAllByNameContainingOrEnglishNameContaining(String name, String englishName);
}
