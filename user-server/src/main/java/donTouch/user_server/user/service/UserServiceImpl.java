package donTouch.user_server.user.service;

import donTouch.user_server.user.domain.JpaUserRepositoryTest;
import donTouch.user_server.user.dto.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl {
    private final JpaUserRepositoryTest jpaUserRepositoryTest;

    public void findUserByEmail() {
        Users users = jpaUserRepositoryTest.findByEmail("email")
            .orElseThrow(()-> new RuntimeException("Email not found"));
    }
}
