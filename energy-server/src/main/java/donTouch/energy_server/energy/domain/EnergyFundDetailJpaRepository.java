package donTouch.energy_server.energy.domain;

import donTouch.energy_server.energy.dto.EnergyFundDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnergyFundDetailJpaRepository extends JpaRepository<EnergyFundDetail, Integer> {
    Optional<EnergyFundDetail> findByEnergyId(@Param("energyId") String id);
}
