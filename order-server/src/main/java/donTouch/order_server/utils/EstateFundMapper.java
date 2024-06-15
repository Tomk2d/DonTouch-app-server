package donTouch.order_server.utils;

import donTouch.order_server.holding.domain.HoldingEstateFund;
import donTouch.order_server.holding.dto.HoldingEstateFundDto;
import donTouch.order_server.holding.dto.HoldingEstateFundForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EstateFundMapper {
    EstateFundMapper INSTANCE = Mappers.getMapper(EstateFundMapper.class);

    HoldingEstateFundDto toDto(HoldingEstateFund holdingEstateFund);
    HoldingEstateFund toEntity(HoldingEstateFundDto holdingEstateFundDto);

    HoldingEstateFund formToEntity(HoldingEstateFundForm holdingEstateFundForm);
}
