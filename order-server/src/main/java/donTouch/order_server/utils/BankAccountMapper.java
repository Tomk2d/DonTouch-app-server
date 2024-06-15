package donTouch.order_server.utils;

import donTouch.order_server.bankAccount.domain.BankAccountLog;
import donTouch.order_server.kafka.dto.BankAccountLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankAccountMapper {
    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    BankAccountLogDto toDto(BankAccountLog bankAccountLog);
    BankAccountLog toEntity(BankAccountLogDto bankAccountLogDto);
}

