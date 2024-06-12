package donTouch.energy_server.energy.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyFundJpaRepository extends JpaRepository<EnergyFund, String> {
}
