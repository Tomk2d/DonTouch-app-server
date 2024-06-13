package donTouch.user_server.user.service;

import donTouch.user_server.user.dto.UsersDto;

public interface UserService {
    UsersDto findUserByEmail(String email);
}
