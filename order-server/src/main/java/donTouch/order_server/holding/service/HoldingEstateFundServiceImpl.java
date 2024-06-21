package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.HoldingEnergyFund;
import donTouch.order_server.holding.domain.HoldingEstateFund;
import donTouch.order_server.holding.domain.HoldingEstateFundRepository;
import donTouch.order_server.holding.dto.*;
import donTouch.order_server.utils.EstateFundMapper;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@AllArgsConstructor
@Service
public class HoldingEstateFundServiceImpl implements HoldingEstateFundService {
    private final HoldingEstateFundRepository holdingEstateFundRepository;
    private final EstateFundMapper estateFundMapper = EstateFundMapper.INSTANCE;

    @Override
    public List<HoldingEstateFundDto> getAllEstate(Long userId) {
        List<HoldingEstateFund> listDto =  holdingEstateFundRepository.findAllByUserId(userId)
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
        HoldingEstateFund savedEstateFund = holdingEstateFundRepository.save(estateFundEntity);
        Optional<HoldingEstateFund> resultCheck = Optional.of(savedEstateFund);
        HoldingEstateFund result = resultCheck
            .orElseThrow(()-> new NullPointerException("저장에 실패하였습니다."));
        return estateFundMapper.toDto(result);
    }

    @Override
    public HoldingEstateFundDto findByUserIdAndEstateFundId(HoldingEstateFundForm holdingEstateFundForm) {
        Long userId = holdingEstateFundForm.getUserId();
        int estateFundId = holdingEstateFundForm.getEstateId();

        HoldingEstateFund holdingEstateFund = holdingEstateFundRepository.findByUserIdAndEstateId(userId, estateFundId)
            .orElseThrow(()-> new NullPointerException("not holding estate fund"));
        holdingEstateFundRepository.delete(holdingEstateFund);
        HoldingEstateFundDto result = estateFundMapper.toDto(holdingEstateFund);
        return result;
    }

    @Override
    public Integer getEstateTotalCash(Long userId) {
        List<HoldingEstateFund> listDto = holdingEstateFundRepository.findAllByUserId(userId)
                .orElseThrow(() -> new NullPointerException("not found for holding energy fund"));

        if(listDto.isEmpty()){
            return null;
        }

        Integer totalCash = 0;
        for(HoldingEstateFund holdingEstateFund : listDto){
            totalCash += holdingEstateFund.getInputCash();
        }
        return totalCash;
    }

    @Override
    public List<DividendP2PDto> getEstateDividend(CalendarReqForm calendarReqForm, String token) {
        List<DividendP2PDto> dividendDtoList = new ArrayList<>();

        //Long userId = JwtUtil.extractId(token);
        List<HoldingEstateFundDto> holdingEstateFundDtoList = getAllEstate(1001L);
        if(holdingEstateFundDtoList==null)
            throw new NullPointerException("보유한 부동산이 없어 배당이 존재하지 않습니다.");

        LocalDate startDate = calendarReqForm.getStartDate();
        LocalDate endDate = calendarReqForm.getEndDate();

        holdingEstateFundDtoList.forEach(holding -> {
            double earningRate = holding.getEarningRate();
            int investmentPeriod = holding.getInvestmentPeriod();
            int inputCash = holding.getInputCash();
            LocalDate buyDate = holding.getCreatedAt().toLocalDate();
            double dividendPrice = inputCash * earningRate / investmentPeriod /100;

            for (long i = 1; i <= investmentPeriod; i++) {
                LocalDate tmpDividendDate = buyDate.plusMonths(i);
                if (checkInPeriod(startDate, endDate, tmpDividendDate)) {
                    DividendP2PDto dividendDto = new DividendP2PDto(
                            String.valueOf(holding.getEstateId()),
                            holding.getTitle(),
                            holding.getTitleImageUrl(),
                            dividendPrice,
                            tmpDividendDate
                    );
                    dividendDtoList.add(dividendDto);
                }
                else if(tmpDividendDate.isAfter(endDate))
                    break;
            }
        });
        return dividendDtoList;
    }
    public boolean checkInPeriod(LocalDate startDate, LocalDate endDate, LocalDate tmpDate){
        return (tmpDate.isEqual(startDate) || tmpDate.isAfter(startDate)) && (tmpDate.isEqual(endDate) || tmpDate.isBefore(endDate));
    }
}
