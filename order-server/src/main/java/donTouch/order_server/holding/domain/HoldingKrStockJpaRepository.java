package donTouch.order_server.holding.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoldingKrStockJpaRepository extends JpaRepository<HoldingKrStock, Long> {
    Optional<HoldingKrStock> findByUserIdAndKrStockId(Long userId, String krStockId);

    List<HoldingKrStock> findAllByUserId(Long userId);
}
