package donTouch.order_server.utils;

import donTouch.order_server.holding.domain.HoldingKrStock;
import donTouch.order_server.holding.domain.KrStockTradingLog;
import donTouch.order_server.holding.dto.HoldingKrStockDto;
import donTouch.order_server.holding.dto.KrStockTradingLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KrStockLogMapper {
    KrStockLogMapper INSTANCE = Mappers.getMapper(KrStockLogMapper.class);

    KrStockTradingLogDto toDto(KrStockTradingLog krStockTradingLog);
    KrStockTradingLog toEntity(KrStockTradingLogDto krStockTradingLogDto);
}

