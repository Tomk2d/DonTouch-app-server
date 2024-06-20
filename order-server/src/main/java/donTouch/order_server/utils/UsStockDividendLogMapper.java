package donTouch.order_server.utils;

import donTouch.order_server.holding.domain.UsStockDividendLog;
import donTouch.order_server.holding.dto.UsStockDividendLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsStockDividendLogMapper {
    UsStockDividendLogMapper INSTANCE = Mappers.getMapper(UsStockDividendLogMapper.class);

    UsStockDividendLog toEntity(UsStockDividendLogDto usStockDividendLogDto);
    UsStockDividendLogDto toDto(UsStockDividendLog usStockDividendLog);
}
