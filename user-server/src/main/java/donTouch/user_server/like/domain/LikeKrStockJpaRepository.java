package donTouch.user_server.like.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeKrStockJpaRepository extends JpaRepository<LikeKrStock, Long> {
    List<LikeKrStock> findAllByUserId(Long userId);

    void deleteByUserIdAndKrStockId(Long userId, Integer stockId);

    Optional<LikeKrStock> findByUserIdAndKrStockId(Long userId, Integer stockId);
}
