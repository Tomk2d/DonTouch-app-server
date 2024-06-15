package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.HoldingEstateFund;
import donTouch.order_server.holding.dto.HoldingEstateFundDto;
import donTouch.order_server.holding.domain.HolidingEstateFundRepository;
import donTouch.order_server.holding.dto.HoldingEstateFundForm;
import donTouch.order_server.utils.EstateFundMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class HoldingEstateFundServiceImpl implements HoldingEstateFundService {
    private final HolidingEstateFundRepository holidingEstateFundRepository;
    private final EstateFundMapper estateFundMapper = EstateFundMapper.INSTANCE;

    @Override
    public List<HoldingEstateFundDto> getAllEstate(Long userId) {
        List<HoldingEstateFund> listDto =  holidingEstateFundRepository.findAllByUserId(userId)
            .orElseThrow(()-> new NullPointerException("not found for holding estate fund"));
        if (listDto.isEmpty()) {
            return null;
        }
        List<HoldingEstateFundDto> holdingEstateFundDtoList = new ArrayList<>();
        for (HoldingEstateFund holdingEstateFund : listDto) {
            holdingEstateFundDtoList.add(estateFundMapper.toDto(holdingEstateFund));
        }
        return holdingEstateFundDtoList;
    }

    @Override
    public HoldingEstateFundDto saveEstate(HoldingEstateFundForm holdingEstateFundForm) {
        HoldingEstateFund estateFundEntity = estateFundMapper.formToEntity(holdingEstateFundForm);
        HoldingEstateFund savedEstateFund = holidingEstateFundRepository.save(estateFundEntity);
        Optional<HoldingEstateFund> resultCheck = Optional.of(savedEstateFund);
        HoldingEstateFund result = resultCheck
            .orElseThrow(()-> new NullPointerException("저장에 실패하였습니다."));
        return estateFundMapper.toDto(result);
    }
}
