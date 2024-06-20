package donTouch.energy_server.energy.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnergyFundJpaRepository extends JpaRepository<EnergyFund, String> {
    List<EnergyFund> findAllByEnergyIdIn(List<String> ids);
}
