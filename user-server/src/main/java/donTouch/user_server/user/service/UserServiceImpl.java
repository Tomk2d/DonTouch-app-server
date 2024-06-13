package donTouch.user_server.user.service;

import donTouch.user_server.user.domain.JpaUserRepository;
import donTouch.user_server.user.domain.Users;
import donTouch.user_server.user.dto.UsersDto;
import donTouch.user_server.user.utils.EntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final JpaUserRepository jpaUserRepository;

    public UsersDto findUserByEmail(String email) {
        Users user = jpaUserRepository.findByEmail(email)
            .orElseThrow(()-> new NullPointerException("User not found"));
        return UsersDto.toDto(user);
    }
}
