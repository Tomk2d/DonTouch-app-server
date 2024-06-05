package donTouch.user_server.user.domain;

import donTouch.user_server.user.dto.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepositoryTest extends JpaRepository<Users, Long>{
    public Optional<Users> findByEmail(String email);
}
