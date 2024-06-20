package donTouch.order_server.utils;

import donTouch.order_server.holding.domain.UsStockTradingLog;
import donTouch.order_server.holding.dto.UsStockTradingLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsStockLogMapper {
    UsStockLogMapper INSTANCE = Mappers.getMapper(UsStockLogMapper.class);

    UsStockTradingLogDto toDto(UsStockTradingLog usStockTradingLog);
    UsStockTradingLog toEntity(UsStockTradingLogDto usStockTradingLogDto);
}
