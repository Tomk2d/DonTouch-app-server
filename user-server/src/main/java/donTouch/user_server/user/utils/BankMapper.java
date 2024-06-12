package donTouch.user_server.user.utils;

import donTouch.user_server.user.domain.BankAccount;
import donTouch.user_server.user.dto.BankAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankMapper {
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    BankAccountDto toDto(BankAccount bankAccount);
    BankAccount toEntity(BankAccountDto bankAccountDto);
}
