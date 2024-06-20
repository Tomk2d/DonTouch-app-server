package donTouch.order_server.utils;

import donTouch.order_server.holding.domain.KrStockDividendLog;
import donTouch.order_server.holding.dto.KrStockDividendLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KrStockDividendLogMapper {
    KrStockDividendLogMapper INSTANCE = Mappers.getMapper(KrStockDividendLogMapper.class);

    KrStockDividendLog toEntity(KrStockDividendLogDto krStockDividendLogDto);
    KrStockDividendLogDto toDto(KrStockDividendLog krStockDividendLog);
}
