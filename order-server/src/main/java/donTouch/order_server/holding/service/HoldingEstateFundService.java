package donTouch.order_server.holding.service;

import donTouch.order_server.holding.dto.HoldingEstateFundDto;
import donTouch.order_server.holding.dto.HoldingEstateFundForm;

import java.util.List;

public interface HoldingEstateFundService {

    List<HoldingEstateFundDto> getAllEstate(Long userId);

    HoldingEstateFundDto saveEstate(HoldingEstateFundForm holdingEstateFundForm);

    HoldingEstateFundDto findByUserIdAndEstateFundId(HoldingEstateFundForm holdingEstateFundForm);
}
