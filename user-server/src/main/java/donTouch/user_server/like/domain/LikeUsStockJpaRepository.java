package donTouch.user_server.like.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeUsStockJpaRepository extends JpaRepository<LikeUsStock, Long> {
    List<LikeUsStock> findAllByUserId(Long userId);

    void deleteByUserIdAndUsStockId(Long userId, Integer usStockId);

    Optional<LikeUsStock> findByUserIdAndUsStockId(Long userId, Integer usStockId);
}
