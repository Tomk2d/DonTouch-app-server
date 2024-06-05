package donTouch.user_server.user.domain;

import donTouch.user_server.user.dto.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface JpaUserRepositoryTest extends JpaRepository<Users, Long>{

}
