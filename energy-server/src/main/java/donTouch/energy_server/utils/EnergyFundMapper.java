package donTouch.energy_server.utils;

import donTouch.energy_server.energy.domain.EnergyFund;
import donTouch.energy_server.energy.dto.EnergyFundDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface EnergyFundMapper {
    EnergyFundMapper INSTANCE = Mappers.getMapper(EnergyFundMapper.class);

    EnergyFundDto toDto(EnergyFund energyFund);
    EnergyFund toEntity(EnergyFundDto energyFundDto);
}
