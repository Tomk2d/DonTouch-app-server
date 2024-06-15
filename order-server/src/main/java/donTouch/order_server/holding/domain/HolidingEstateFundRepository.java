package donTouch.order_server.holding.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidingEstateFundRepository extends JpaRepository<HoldingEstateFund, Integer> {
    public Optional<List<HoldingEstateFund>> findAllByUserId(Long userId);
}
