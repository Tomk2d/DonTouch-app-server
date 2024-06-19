package donTouch.energy_server.energy.service;

import donTouch.energy_server.energy.dto.BuyEnergyFundForm;
import donTouch.energy_server.energy.dto.EnergyFundDetailDto;
import donTouch.energy_server.energy.dto.EnergyFundDto;

import java.util.List;

public interface EnergyFundService {
    List<EnergyFundDto> getAllEnergyFund();

    Boolean buyEnergyFund(BuyEnergyFundForm buyEnergyFundForm);

    Boolean sellEnergyFund(BuyEnergyFundForm buyEnergyFundForm);
}
