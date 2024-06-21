package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.HoldingEnergyFund;
import donTouch.order_server.holding.domain.HoldingEnergyFundRepository;
import donTouch.order_server.holding.dto.CalendarReqForm;
import donTouch.order_server.holding.dto.DividendP2PDto;
import donTouch.order_server.holding.dto.HoldingEnergyFundDto;
import donTouch.order_server.holding.dto.HoldingEnergyFundForm;
import donTouch.order_server.utils.EnergyFundMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class HoldingEnergyFundServiceImpl implements HoldingEnergyFundService{
    private final HoldingEnergyFundRepository holdingEnergyFundRepository;
//    private final JwtUtil jwtUtil;
    private final EnergyFundMapper energyFundMapper = EnergyFundMapper.INSTANCE;

    @Override
    public List<HoldingEnergyFundDto> getAllEnergy(Long userId) {
        List<HoldingEnergyFund> listDto = holdingEnergyFundRepository.findAllByUserId(userId)
                .orElseThrow(() -> new NullPointerException("not found for holding energy fund"));

        if(listDto.isEmpty()){
            return null;
        }

        List<HoldingEnergyFundDto> holdingEnergyFundDtoList = new ArrayList<>();
        for(HoldingEnergyFund holdingEnergyFund : listDto){
            holdingEnergyFundDtoList.add(energyFundMapper.toDto(holdingEnergyFund));
        }
        return holdingEnergyFundDtoList;
    }

    @Override
    public Integer getEnergyTotalCash(Long userId) {
        List<HoldingEnergyFund> listDto = holdingEnergyFundRepository.findAllByUserId(userId)
                .orElseThrow(() -> new NullPointerException("not found for holding energy fund"));

        if(listDto.isEmpty()){
            return null;
        }

        Integer totalCash = 0;
        for(HoldingEnergyFund holdingEnergyFund : listDto){
            totalCash += holdingEnergyFund.getInputCash();
        }
        return totalCash;
    }

    public HoldingEnergyFundDto saveEnergy(HoldingEnergyFundForm holdingEnergyFundForm) {
        HoldingEnergyFund holdingEnergyFund = energyFundMapper.formToEnergy(holdingEnergyFundForm);
        HoldingEnergyFund savedEnergyFund = holdingEnergyFundRepository.save(holdingEnergyFund);
        Optional<HoldingEnergyFund> resultCheck = Optional.of(savedEnergyFund);
        HoldingEnergyFund result = resultCheck
                .orElseThrow(()-> new NullPointerException("저장에 실패하였습니다."));
        return energyFundMapper.toDto(result);
    }

    @Override
    public HoldingEnergyFundDto findByUserIdAndEnergyFundId(HoldingEnergyFundForm holdingEnergyFundForm) {
        Long userId = holdingEnergyFundForm.getUserId();
        String energyId = holdingEnergyFundForm.getEnergyId();

        HoldingEnergyFund holdingEnergyFund = holdingEnergyFundRepository.findByUserIdAndEnergyId(userId,energyId)
                .orElseThrow(() -> new NullPointerException("not holding energy fund"));

        holdingEnergyFundRepository.delete(holdingEnergyFund);
        HoldingEnergyFundDto result = energyFundMapper.toDto(holdingEnergyFund);
        return result;
    }

    @Override
    public List<DividendP2PDto> getEnergyDividend(CalendarReqForm calendarReqForm, String token) {
        List<DividendP2PDto> dividendDtoList = new ArrayList<>();

        //Long userId = JwtUtil.extractId(token);
        List<HoldingEnergyFundDto> holdingEnergyFundDtoList = getAllEnergy(1001L);
        if(holdingEnergyFundDtoList==null)
            throw new NullPointerException("보유한 에너지가 없어 배당이 존재하지 않습니다.");

        LocalDate startDate = calendarReqForm.getStartDate();
        LocalDate endDate = calendarReqForm.getEndDate();

        holdingEnergyFundDtoList.forEach(holding -> {
            double earningRate = holding.getEarningRate();
            int investmentPeriod = holding.getInvestmentPeriod();
            int inputCash = holding.getInputCash();
            LocalDate buyDate = holding.getCreatedAt().toLocalDate();
            double dividendPrice = (inputCash * 0.01* earningRate) / investmentPeriod;

            for (long i = 1; i <= investmentPeriod; i++) {
                LocalDate tmpDividendDate = buyDate.plusMonths(i);

                if (checkInPeriod(startDate, endDate, tmpDividendDate)) {
                    DividendP2PDto dividendDto = new DividendP2PDto(
                            holding.getEnergyId(),
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
