package donTouch.order_server.holding.service;

import donTouch.order_server.holding.domain.HoldingEnergyFund;
import donTouch.order_server.holding.domain.HoldingEnergyFundRepository;
import donTouch.order_server.holding.dto.HoldingEnergyFundDto;
import donTouch.order_server.holding.dto.HoldingEnergyFundForm;
import donTouch.order_server.utils.EnergyFundMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class HoldingEnergyFundServiceImpl implements HoldingEnergyFundService{
    private final HoldingEnergyFundRepository holdingEnergyFundRepository;
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
}
