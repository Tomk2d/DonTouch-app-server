package donTouch.user_server.like.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeEnergyFundJpaRepository extends JpaRepository<LikeEnergyFund, Long> {
    List<LikeEnergyFund> findAllByUserId(Long userId);

    void deleteByUserIdAndEnergyFundId(Long userId, String fundId);

    Optional<LikeEnergyFund> findByUserIdAndEnergyFundId(Long userId, String fundId);
}
