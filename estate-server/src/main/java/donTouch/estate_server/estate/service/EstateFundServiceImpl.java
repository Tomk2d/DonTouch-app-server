package donTouch.estate_server.estate.service;

import donTouch.estate_server.estate.domain.EstateFund;
import donTouch.estate_server.estate.domain.EstateFundJpaRepository;
import donTouch.estate_server.estate.dto.EstateFundDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EstateFundServiceImpl implements EstateFundService {
    private final EstateFundJpaRepository estateFundRepository;


    @Override
    public List<EstateFundDto> getAllEstateFund() {
        List<EstateFund> estateFundList = estateFundRepository.findAll();
        if (estateFundList.isEmpty()) {
            throw new NullPointerException("EstateFund List is empty");
        }
        List<EstateFundDto> estateFundDtoList = new ArrayList<>();
        estateFundList.forEach(estateFund -> {
            EstateFundDto dto = EstateFundDto.toDto(estateFund);
            estateFundDtoList.add(dto);
        });
        return estateFundDtoList;
    }
}
