package donTouch.order_server.utils;

import donTouch.order_server.holding.domain.HoldingEstateFund;
import donTouch.order_server.holding.domain.HoldingKrStock;
import donTouch.order_server.holding.dto.HoldingEstateFundDto;
import donTouch.order_server.holding.dto.HoldingEstateFundForm;
import donTouch.order_server.holding.dto.HoldingKrStockDto;
import donTouch.order_server.holding.dto.HoldingKrStockFindForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KrStockMapper {
    KrStockMapper INSTANCE = Mappers.getMapper(KrStockMapper.class);

    HoldingKrStockDto toDto(HoldingKrStock holdingKrStock);
    HoldingKrStock toEntity(HoldingKrStockDto holdingKrStockDto);
    HoldingKrStock formToEntity(HoldingKrStockFindForm holdingKrStockFindForm);
}
