package donTouch.order_server.holding.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoldingEnergyFundRepository extends JpaRepository<HoldingEnergyFund, String> {
    public Optional<List<HoldingEnergyFund>> findAllByUserId(Long userId);

    Optional<HoldingEnergyFund> findByUserIdAndEnergyId(Long userId, String EnergyId);
}
