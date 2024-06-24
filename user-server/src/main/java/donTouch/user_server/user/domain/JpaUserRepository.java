package donTouch.user_server.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findByEmail(String email);

}
