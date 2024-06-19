package donTouch.stock_server.krStock.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KrLatestCloseJpaRepository extends JpaRepository<KrLatestClose, Integer> {
    Optional<KrLatestClose> findByKrStockId(Integer krStockId);
}
