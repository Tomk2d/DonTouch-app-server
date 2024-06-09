package donTouch.user_server.user.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<Users, Long>{
    public Optional<Users> findByEmail(String email);
}
