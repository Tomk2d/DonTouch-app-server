package donTouch.estate_server.estate.utils;

import donTouch.estate_server.estate.domain.EstateFund;
import donTouch.estate_server.estate.dto.EstateFundDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EstateFundMapper {
    EstateFundMapper INSTANCE = Mappers.getMapper(EstateFundMapper.class);

    EstateFundDto toDto(EstateFund estateFund);
    EstateFund toEntity(EstateFundDto estateFundDto);
}
