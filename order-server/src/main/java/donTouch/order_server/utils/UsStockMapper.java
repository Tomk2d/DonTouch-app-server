package donTouch.order_server.utils;

import donTouch.order_server.holding.domain.HoldingUsStock;
import donTouch.order_server.holding.dto.HoldingUsStockDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsStockMapper {
    UsStockMapper INSTANCE = Mappers.getMapper(UsStockMapper.class);

    HoldingUsStock toEntity(HoldingUsStockDto holdingUsStockDto);
    HoldingUsStockDto toDto(HoldingUsStock usStock);
}
