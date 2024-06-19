package donTouch.order_server.holding.service;

import donTouch.order_server.holding.dto.HoldingEnergyFundDto;
import donTouch.order_server.holding.dto.HoldingEnergyFundForm;

import java.util.List;

public interface HoldingEnergyFundService {
    List<HoldingEnergyFundDto> getAllEnergy(Long userId);

    HoldingEnergyFundDto saveEstate(HoldingEnergyFundForm holdingEnergyFundForm);

    HoldingEnergyFundDto findByUserIdAndEnergyFundId(HoldingEnergyFundForm holdingEnergyFundForm);
}
