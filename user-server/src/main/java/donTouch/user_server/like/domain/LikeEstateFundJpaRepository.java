package donTouch.user_server.like.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeEstateFundJpaRepository extends JpaRepository<LikeEstateFund, Long> {
    List<LikeEstateFund> findAllByUserId(Long userId);

    void deleteByUserIdAndEstateFundId(Long userId, Integer fundId);

    Optional<LikeEstateFund> findByUserIdAndEstateFundId(Long userId, Integer fundId);
}
