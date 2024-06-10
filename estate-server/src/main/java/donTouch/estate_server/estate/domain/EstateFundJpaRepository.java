package donTouch.estate_server.estate.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateFundJpaRepository extends JpaRepository<EstateFund, Integer> {

}
