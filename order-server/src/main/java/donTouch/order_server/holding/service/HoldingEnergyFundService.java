package donTouch.order_server.holding.service;

import donTouch.order_server.holding.dto.CalendarReqForm;
import donTouch.order_server.holding.dto.DividendEnergyDto;
import donTouch.order_server.holding.dto.HoldingEnergyFundDto;
import donTouch.order_server.holding.dto.HoldingEnergyFundForm;

import java.util.List;

public interface HoldingEnergyFundService {
    List<HoldingEnergyFundDto> getAllEnergy(Long userId);

    Integer getEnergyTotalCash(Long userId);

    HoldingEnergyFundDto saveEnergy(HoldingEnergyFundForm holdingEnergyFundForm);

    HoldingEnergyFundDto findByUserIdAndEnergyFundId(HoldingEnergyFundForm holdingEnergyFundForm);

    List<DividendEnergyDto> getEnergyDividend(CalendarReqForm calendarReqForm, String token);

}
