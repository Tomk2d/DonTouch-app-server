package donTouch.stock_server.usStock.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsLatestCloseJpaRepository extends JpaRepository<UsLatestClose, Integer> {
    Optional<UsLatestClose> findByUsStockId(Integer usStockId);
}
