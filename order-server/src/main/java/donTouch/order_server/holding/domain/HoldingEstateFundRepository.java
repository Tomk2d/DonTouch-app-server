package donTouch.order_server.holding.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HoldingEstateFundRepository extends JpaRepository<HoldingEstateFund, Integer> {
    public Optional<List<HoldingEstateFund>> findAllByUserId(Long userId);

    Optional<HoldingEstateFund> findByUserIdAndEstateId(Long userId, int estateId);
}