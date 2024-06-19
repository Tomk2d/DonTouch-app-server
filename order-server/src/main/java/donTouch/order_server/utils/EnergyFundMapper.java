package donTouch.order_server.utils;

import donTouch.order_server.holding.domain.HoldingEnergyFund;
import donTouch.order_server.holding.dto.HoldingEnergyFundDto;
import donTouch.order_server.holding.dto.HoldingEnergyFundForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnergyFundMapper {
    EnergyFundMapper INSTANCE = Mappers.getMapper(EnergyFundMapper.class);

    HoldingEnergyFundDto toDto(HoldingEnergyFund holdingEnergyFund);
    HoldingEnergyFund toEntity(HoldingEnergyFundDto holdingEnergyFundDto);

    HoldingEnergyFund formToEnergy(HoldingEnergyFundForm holdingEnergyFundForm);
}

