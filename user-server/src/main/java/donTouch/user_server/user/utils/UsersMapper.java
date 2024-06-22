package donTouch.user_server.user.utils;


import donTouch.user_server.user.domain.Users;
import donTouch.user_server.user.dto.UsersDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersMapper {
    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    Users toEntity(UsersDto usersDto);
    UsersDto toDto(Users users);
}
