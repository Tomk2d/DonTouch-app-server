package donTouch.order_server.holding.service;

import donTouch.order_server.holding.dto.CalendarReqForm;
import donTouch.order_server.holding.dto.DividendP2PDto;
import donTouch.order_server.holding.dto.HoldingEstateFundDto;
import donTouch.order_server.holding.dto.HoldingEstateFundForm;

import java.util.List;

public interface HoldingEstateFundService {

    List<HoldingEstateFundDto> getAllEstate(Long userId);

    HoldingEstateFundDto saveEstate(HoldingEstateFundForm holdingEstateFundForm);

    HoldingEstateFundDto findByUserIdAndEstateFundId(HoldingEstateFundForm holdingEstateFundForm);

    Integer getEstateTotalCash(Long userId);

    List<DividendP2PDto> getEstateDividend(CalendarReqForm calendarReqForm, String token);

}
