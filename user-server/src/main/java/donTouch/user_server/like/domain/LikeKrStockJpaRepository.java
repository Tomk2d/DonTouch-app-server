package donTouch.user_server.like.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeKrStockJpaRepository extends JpaRepository<LikeKrStock, Long> {
    List<LikeKrStock> findAllByUserId(Long userId);
}
